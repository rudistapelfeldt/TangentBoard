package com.tangent.assessment.tangentboard.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.adapter.EmployeeRecyclerViewAdapter;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.model.Employee;
import com.tangent.assessment.tangentboard.model.StatisticsData;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {

    private static final String TAG = StatisticsFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private EmployeeRecyclerViewAdapter mAdapter;

    private View mView;

    private ArrayList<Employee> mEmployeeList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsFragment newInstance(String param1, String param2) {
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_statistics, container, false);

            //RECYCLERVIEW REFERENCE
            mRecyclerView = (RecyclerView)mView.findViewById(R.id.stats_rv);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //SET RECYCLERVIEW DATA LIST
            setEmployeeRecyclerView();


        }
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void setEmployeeRecyclerView(){

        Observable<List<StatisticsData>> observable = RetrofitClient.getInstance(getActivity(), true).getApiService().getEmployees();

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StatisticsData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(List<StatisticsData> employeeData) {

                        for (StatisticsData data : employeeData){
                            Employee employee = new Employee();
                            employee.setmId(data.getmUser().getId());
                            employee.setmFname(data.getmUser().getFirstName());
                            employee.setmLname(data.getmUser().getLastName());
                            employee.setmPosition(data.getmPosition().getmName());
                            employee.setmEmail(data.getmEmail());
                            employee.setmAge(data.getmAge());

                            //ADD EMPLOYEE OBJECT TO LIST FOR USE IN THE RECYCLERVIEW ADAPTER
                            mEmployeeList.add(employee);
                        }

                        mAdapter = new EmployeeRecyclerViewAdapter(getActivity(), mEmployeeList);

                        //SET RECYCLERVIEW ADAPTER
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
    }
}
