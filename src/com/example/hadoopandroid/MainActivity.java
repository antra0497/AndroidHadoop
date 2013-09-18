package com.example.hadoopandroid;

import org.apache.hadoop.hdfs.server.datanode.DataNode;



//import org.apache.hadoop.hdfs.server.datanode.SecureDataNodeStarter.SecureResources;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import 	android.os.AsyncTask;

public class MainActivity extends Activity {
	
	class startProcess extends AsyncTask<Void, Void, Void> {
		DataNode datanode;
        String args[] = null;

		protected Void doInBackground(Void... arg) {
			try {
				datanode = DataNode.createDataNode(args, null);
				if (datanode != null) {
					//created own join function above because one in DataNode.java was unaccessible
					System.out.println("DATANODE CREATED");
					Log.i("MainActivity", "DATANODE CREATED");
					//datanode.joinDataNode();
				} else {
					System.out.println("DATANODE FAILED");
					Log.i("MainActivity", "DATANODE FAILED");				
				}
			} catch (Throwable e) {		
				Log.e("Main", "Exception Raised in Android MainActivity  "+e.toString());
			} finally {
			}
			return null;
			
	   
	     }

	     protected void onProgressUpdate() {
	        
	     }

	     protected void onPostExecute() {
	    	 datanode.joinDataNode();
	     }

	
	}
	
	  
	  
             
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new startProcess().execute();

        /*
        String args[] = null;
       
       
		try {
			DataNode datanode = DataNode.createDataNode(args, null);
			if (datanode != null) {
				//created own join function above because one in DataNode.java was unaccessible
				System.out.println("DATANODE CREATED");
				Log.i("MainActivity", "DATANODE CREATED");
				datanode.joinDataNode();
			} else {
				System.out.println("DATANODE FAILED");
				Log.i("MainActivity", "DATANODE FAILED");				
			}
		} catch (Throwable e) {		
			Log.e("Main", "Exception Raised in Android MainActivity  "+e.toString());
		} finally {
		}*/
        

        
    }
    	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
