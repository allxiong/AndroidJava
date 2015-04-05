package com.example.dialogex;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DialogExActivity extends Activity {
	
	static final int DIALOG_QUIT = 0;
	static final int DIALOG_CHOOSE = 1;
	
	public TextView tv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Inflates the menu from the resource */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public Dialog onCreateDialog(int id) {
    	
    	Dialog retval;
    	tv = (TextView) findViewById(R.id.textView1);
    	
    	switch(id) {
    	case DIALOG_QUIT:
    		AlertDialog.Builder quitDialog = new AlertDialog.Builder(this);
    		//there are only 3 types of button: Positive, Negative, and Neutral
    		//only one button of each type is allowed, so we're limited here to 
    		//having three buttons.
    		quitDialog.setMessage("Really Quit?")
    		   .setCancelable(false)
    		   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface dialog, int which) {
						DialogExActivity.this.finish();
					}
				})
			   .setNegativeButton("No", new DialogInterface.OnClickListener() {
				
					public void onClick(DialogInterface dialog, int which) {
						tv.setText("Quit Cancelled.");
						dialog.cancel();
					}
				});
    		AlertDialog quitAlertDialog = quitDialog.create();
    		retval = quitAlertDialog;
    		break;
    	case DIALOG_CHOOSE:
    		//to have more than three selections in an alert dialog, use a
    		//list of items:
    		final CharSequence[] choices = {"Apple", "Peach", "Lime", "Strawberry"};
    		AlertDialog.Builder chooseDialog = new AlertDialog.Builder(this);
    		chooseDialog.setTitle("Choose a Flovor:")
    			.setItems(choices, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						tv.setText("You've chosen " + choices[which]);
					}
				});
    		AlertDialog chooseAlertDialog = chooseDialog.create();
    		retval = chooseAlertDialog;
    		break;
    	default:
    		retval = super.onCreateDialog(id);
    	}
    	return retval;
    }
    
    
    /** called when a menu choice is made */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	tv = (TextView) findViewById(R.id.textView1);
    	
    	/* You will see examples that "return" from every case. My preference
    	 * is to return only once from a method, so set up a single return value, 
    	 * and set that within the switch:
    	 */
    	boolean retval;
    	
    	/* use a switch here, rather than a chain of if..else if.. statements.
    	 * Switch results in cleaner looking code.
    	 */
    	switch (item.getItemId()) {
	    	case R.id.show_quit:
	    		tv.setText("Quitting Activity...");
	    		showDialog(DIALOG_QUIT);
	    		retval = true;
	    		break;
	    	case R.id.show_choose:
	    		showDialog(DIALOG_CHOOSE);
	    		retval = true;
	    		break;
	    	default:
	    		/* call onOptionsItemSelected on super - this is better than
	    		 * just returning false, even though it is most likely that
	    		 * super will not respond to a menu item selection. (It might...)
	    		 */
	    		retval = super.onOptionsItemSelected(item);
    	}
    	return retval;
    }
    
    
}
