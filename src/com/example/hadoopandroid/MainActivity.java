package com.example.hadoopandroid;

import org.apache.hadoop.hdfs.server.datanode.DataNode;
//import org.apache.hadoop.hdfs.server.datanode.SecureDataNodeStarter.SecureResources;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


public class MainActivity extends Activity {
	/*    
	void join(DataNode datanode) {
		if (datanode.dataNodeThread != null) {
			try {
				//getDataNodeThread() was added to Hadoop code or dataNodeThread was made public
				datanode.dataNodeThread.join();
				Log.v("Main", "Data Node Thread started");
			} catch (InterruptedException e) {}
		}
		Log.v("Main", "Data Node Thread fail to start");
	}*/
	/*
	void join(DataNode datanode) {
		while (datanode.shouldRun) {
			try {
				datanode.blockPoolManager.joinAll();
		        if (datanode.blockPoolManager.getAllNamenodeThreads() != null && datanode.blockPoolManager.getAllNamenodeThreads().length == 0) {
		        	datanode.shouldRun = false;
		        }
		        Thread.sleep(2000);
		      } catch (InterruptedException ex) {
		      }
	    }
	}*/
	  
	  
             
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String args[] = null;
        //SecureResources resources = null;
        
		try {
			DataNode datanode = DataNode.createDataNode(args, null);
			if (datanode != null) {
				//created own join function above because one in DataNode.java was unaccessible
				System.out.println("DATANODE CREATED");
				Log.i("MainActivity", "DATANODE CREATED");
				datanode.joinDataNode();
				//join(datanode);
			} else {
				System.out.println("DATANODE FAILED");
				Log.i("MainActivity", "DATANODE FAILED");				
			}
		} catch (Throwable e) {		
			Log.e("Main", e.toString());
		} finally {
		}
        
        /*try {
            DataNode datanode = DataNode.createDataNode(args, null, resources);
            if (datanode != null)
            	datanode.join();
          } catch (Throwable e) {
          } finally {
          }*/
        
        
        //DataNode.secureMain(args, resources);
        
    }
    	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}