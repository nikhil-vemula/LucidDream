package lightningstreaks.com.luciddream;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter instituteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Dialog dialog = new Dialog(MainActivity.this, android.R.style.DeviceDefault_Light_ButtonBar);
        dialog.setContentView(R.layout.full_screen_dialog_location);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        ListView locationListView = (ListView) dialog.findViewById(R.id.location_list_view);
        String locations[] = new String[]{"hi","bye"};
        ArrayAdapter locationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,locations);
        locationListView.setAdapter(locationAdapter);
        ListView instituteListView = (ListView) findViewById(R.id.institute_list_view);
        String institutes[] = new String[]{"a","b","c","d","e"};
        instituteAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,institutes);
        instituteListView.setAdapter(instituteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.LEFT));
        searchView.setQueryHint("Search Institute");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                instituteAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
