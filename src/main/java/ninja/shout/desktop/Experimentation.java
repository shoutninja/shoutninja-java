package ninja.shout.desktop;

import java.util.concurrent.CountDownLatch;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Experimentation {
	public static void main(String[] args) {
		// Get a reference to our posts
		Firebase ref = new Firebase("https://eakjb-shout-ninja2.firebaseio.com/settings");

		// Attach an listener to read the data at our posts reference
		final CountDownLatch done = new CountDownLatch(1);
		
		ref.addValueEventListener(new ValueEventListener() {
		    public void onDataChange(DataSnapshot snapshot) {
		        System.out.println(snapshot.getValue());
		        done.countDown();
		    }

		    public void onCancelled(FirebaseError firebaseError) {
		        System.out.println("The read failed: " + firebaseError.getMessage());
		    }
		});
		
		try {
			done.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
