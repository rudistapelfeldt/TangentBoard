package com.tangent.assessment.tangentboard.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.model.StatisticsData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private TextView mNoOfEmp, mNoOfMEmp, mNoOfFEmp, mNoOfBirthdays;

    private TextView mNoOfPms, mNoOfFe, mNoOfBe;

    private Map<String, Object> mNoOfMEmpMap = new HashMap<String, Object>();

    private Map<String, Object> mNoOfFEmpMap = new HashMap<String, Object>();

    private Map<String, Object> mNoOfBirthdaysMap = new HashMap<String, Object>();

    private Map<String, Object> mNoOfPmsMap = new HashMap<String, Object>();

    private Map<String, Object> mNoOfFeMap = new HashMap<String, Object>();

    private Map<String, Object> mNoOfBeMap = new HashMap<String, Object>();

    private View mView;


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

            //TEXTVIEW REFERENCES
            mNoOfBe = (TextView)mView.findViewById(R.id.stats_no_of_be);
            mNoOfBirthdays = (TextView)mView.findViewById(R.id.stats_no_of_birthdays);
            mNoOfEmp = (TextView)mView.findViewById(R.id.stats_no_of_emp);
            mNoOfFe = (TextView)mView.findViewById(R.id.stats_no_of_fe);
            mNoOfFEmp = (TextView)mView.findViewById(R.id.stats_no_of_female);
            mNoOfMEmp = (TextView)mView.findViewById(R.id.stats_no_of_male);
            mNoOfPms = (TextView)mView.findViewById(R.id.stats_no_of_pms);

            //GET STATISTICS AND SET TEXTVIEWS
            mNoOfPmsMap.put("position", 3);
            getEmployees(mNoOfPmsMap, mNoOfPms);

            mNoOfFeMap.put("position", 1);
            getEmployees(mNoOfFeMap, mNoOfFe);

            mNoOfBeMap.put("position", 2);
            getEmployees(mNoOfBeMap, mNoOfBe);

            mNoOfBirthdaysMap.put("birth_date_range", 3);
            getEmployees(mNoOfBirthdaysMap, mNoOfBirthdays);

            mNoOfFEmpMap.put("gender", "F");
            getEmployees(mNoOfFEmpMap, mNoOfFEmp);

            mNoOfMEmpMap.put("gender", "M");
            getEmployees(mNoOfMEmpMap, mNoOfMEmp);

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



    protected void getEmployees(Map<String, Object> map, final TextView textView){

        Observable<List<StatisticsData>> observable = RetrofitClient.getInstance(getActivity(), true).getApiService().getEmployeesMap(map);

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
                        Log.i(TAG, "got data");
                        textView.setText(String.valueOf(employeeData.size()));
                    }
                });
    }
}
