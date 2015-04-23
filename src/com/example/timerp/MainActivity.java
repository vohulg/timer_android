package com.example.timerp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {

  private ImageView imView;
   private TextView tvCnt;
   private Button butStart;
   private Button btnPause;
   private CountDownTimer timer;
   private boolean isPaused = false;
   private long restMillisUntilFinished = 0;
   
   private static final int SecVal = 30;
   private static final int MillisInSec = 1000;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvCnt = (TextView)findViewById(R.id.tvCount);
        butStart = (Button)findViewById(R.id.bStart);
        btnPause = (Button)findViewById(R.id.btnPause);
        imView = (ImageView)findViewById(R.id.imageView1);
        
        OnClickListener ocLBtn = new OnClickListener() 
        {
        	

			@Override
			public void onClick(View v)
			{
				int id = v.getId();        		
        		switch(id)
        		{
        		   case R.id.bStart:showTimer(SecVal * MillisInSec);break;
        		   case R.id.btnPause:if (timer != null) timer.cancel() ; isPaused = true; break;
        		
        		}
				
			}  
        	
        };
        
        butStart.setOnClickListener(ocLBtn);
        btnPause.setOnClickListener(ocLBtn);
        
	}  
	

       
	
//------------------------------ таймер, показывает количество оставшихся секунд------------------------------------------------//
	private void showTimer(int count)
	{
		if (timer != null) timer.cancel();
		
		if (isPaused == true)
		{
			count = (int)restMillisUntilFinished; 
		}
		
		timer = new CountDownTimer(count, MillisInSec) {
			
			@Override
			public void onTick(long millisUntilFinished)
			{
				restMillisUntilFinished = millisUntilFinished;
				String res = Integer.toString((int)(millisUntilFinished / MillisInSec));
				tvCnt.setText(res );
				
			}
			
			@Override
			public void onFinish()
			{
				tvCnt.setText("finished");
				
			}
		};
		
		timer.start();
		
		
		
	}

	
	
//=====================================================================================================================================//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
