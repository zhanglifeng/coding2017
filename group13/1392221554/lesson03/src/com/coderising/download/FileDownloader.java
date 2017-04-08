package com.coderising.download;

import java.io.RandomAccessFile;

import jdk.nashorn.internal.ir.Flags;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

public class FileDownloader {
	
	String url;
	DownloadListener listener;
	ConnectionManager cm;
    private RandomAccessFile raf;// �����ص����ֽ������raf��  
    final int DOWN_THREAD_NUM = 3;//���弸���߳�ȥ����   
    Connection[] conn = new ConnectionImpl[DOWN_THREAD_NUM];
    DownloadThread[] threads = new DownloadThread[DOWN_THREAD_NUM];//�̳߳�
    
	public FileDownloader(String _url) {
		this.url = _url;		
	}
	
	public void execute(){
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿ�, ����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// (1) ConnectionManager , ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���startPos, endPos��ָ����
		// (2) DownloadListener, �����Ƕ��߳����أ� ���������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ�����
		//     �̶߳�ִ�����Ժ� ����listener��notifiedFinished������ �����ͻ��˾����յ�֪ͨ��
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager��open���������ӣ� Ȼ��ͨ��Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ�  ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// Ȼ�����read������ read�������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ����� ����ֵ��byte[]����
		// 3. ��byte����д�뵽�ļ���
		// 4. ���е��̶߳���������Ժ� ��Ҫ����listener��notifiedFinished����
		
		// ����Ĵ�����ʾ�����룬 Ҳ����˵ֻ��һ���̣߳� ����Ҫ����ɶ��̵߳ġ�

		try {
			conn[0] = cm.open(this.url);
			long fileLen = conn[0].getContentLength();
            long numPerThred = fileLen / DOWN_THREAD_NUM;  
            long left = fileLen % DOWN_THREAD_NUM;  
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {  
                if (i != 0) {  
                    conn[i] = cm.open(this.url);  
                }  
                if (i == DOWN_THREAD_NUM - 1) {  
                	threads[i] = new DownloadThread(conn[i],i * numPerThred,(i + 1) * numPerThred+left); 
                } else {  
                	threads[i] = new DownloadThread(conn[i],i * numPerThred,(i + 1) * numPerThred);
                }  
                threads[i].start();
            }
            boolean finished = false;
            int finishedCount;
            while(!finished){
            	finishedCount = 0;
                for (DownloadThread t : threads) {
    				if(t.getStatus() == "finished")
    					finishedCount ++;
    			}
                if(DOWN_THREAD_NUM == finishedCount)
                	break;
            }
            listener.notifyFinished();
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				for (Connection o : conn) {
					o.close();
				}
			}
		}
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}
