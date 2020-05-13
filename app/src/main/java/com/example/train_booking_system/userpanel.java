package com.example.train_booking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.train_booking_system.Fragments.BookingFragment;
import com.example.train_booking_system.Fragments.HomeFragment;
import com.example.train_booking_system.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class userpanel extends AppCompatActivity {

    BottomNavigationView objBNV;

    HomeFragment objHome;
    BookingFragment objBooking;
    ProfileFragment objProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpanel);

        Connection();
        SetBNV();

        ChangeFragments(objHome);
    }

    public void Connection()
    {
        try
        {
            objBNV=findViewById(R.id.BNV);

            objHome = new HomeFragment();
            objBooking = new BookingFragment();
            objProfile = new ProfileFragment();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Connection: "
                    +e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void SetBNV()
    {
        try
        {
            objBNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId())
                    {
                        case R.id.Homeitem:
                            Toast.makeText(userpanel.this, "Home Selected", Toast.LENGTH_SHORT).show();
                            ChangeFragments(objHome);
                            return true;
                        case R.id.Bookeditem:
                            Toast.makeText(userpanel.this, "Booked Selected", Toast.LENGTH_SHORT).show();
                            ChangeFragments(objBooking);
                            return true;
                        case R.id.Profileitem:
                            Toast.makeText(userpanel.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                            ChangeFragments(objProfile);
                            return true;
                    }
                    return false;
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Fragments", Toast.LENGTH_SHORT).show();
        }
    }
    private void ChangeFragments(Fragment objFrag)
    {
        try
        {
            FragmentTransaction objFragmentTransaction = getSupportFragmentManager().beginTransaction();
            objFragmentTransaction.replace(R.id.FL, objFrag);
            objFragmentTransaction.commit();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void Logout(View view) {
        try
        {
            startActivity(new Intent(this, MainActivity.class));
        }
        catch (Exception e)
        {
            Toast.makeText(this, "MovetoSignUp: "
                    +e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
