package com.example.stickylistheader;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stickylistheader.adapter.PersonAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new NameListFragment())
                    .commit();
        }
    }

    public static class NameListFragment extends Fragment {

        private static final List<String> persons = Arrays.asList("Jan Kowalski", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny", "John Q", "Ewa Koloratek", "Adam Wolny");

        private PersonAdapter personAdapter;
        private TextView topHeader;
        private ListView listView;
        private int topVisibleCount;

        public NameListFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Collections.sort(persons);
            personAdapter = new PersonAdapter(this.getActivity(), persons);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            topHeader = (TextView) rootView.findViewById(R.id.tv_header_title);
            listView = (ListView) rootView.findViewById(android.R.id.list);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            topHeader.setText(persons.get(topVisibleCount).substring(0,1));

            listView.setAdapter(personAdapter);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if(firstVisibleItem != topVisibleCount) {
                        topVisibleCount = firstVisibleItem;
                        topHeader.setText(persons.get(topVisibleCount).substring(0,1));
                    }
                }
            });

        }
    }

}
