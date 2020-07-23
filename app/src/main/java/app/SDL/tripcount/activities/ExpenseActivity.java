package app.SDL.tripcount.activities;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import app.SDL.tripcount.R;
import app.SDL.tripcount.adapters.SectionPageAdapter;
import app.SDL.tripcount.fragments.BalanceFragment;
import app.SDL.tripcount.fragments.CustomBottomSheetDialogFragment;
import app.SDL.tripcount.fragments.ExpenseFragment;
import app.SDL.tripcount.home;

public class ExpenseActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    public static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);


        mViewPager=findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");

            }
        });

    }




    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExpenseFragment(),"Expenses");
        adapter.addFragment(new BalanceFragment(),"Balance");
        viewPager.setAdapter(adapter);
    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ExpenseActivity.this, home.class));
        finish();

    }



}
