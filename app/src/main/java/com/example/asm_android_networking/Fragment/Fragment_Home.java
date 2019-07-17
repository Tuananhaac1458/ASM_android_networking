package com.example.asm_android_networking.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asm_android_networking.Adapter.Recyclerview_adapter;
import com.example.asm_android_networking.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ////// Khai bao/////////////////////
    View v;
    private static final String TAG ="Fragment_Home";
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mStatus = new ArrayList<>();
    private ArrayList<String> mTacgias = new ArrayList<>();
    private ArrayList<String> mCategoris = new ArrayList<>();
    private RecyclerView recyclerview_home;


    private OnFragmentInteractionListener mListener;

    public Fragment_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
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

        initImageBitmap();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
               v = inflater.inflate(R.layout.fragment_fragment__home, container, false);
                recyclerview_home = v.findViewById(R.id.recyclerview_home);
                Recyclerview_adapter adapter = new Recyclerview_adapter(getContext(),mImages,mNames,mStatus,mTacgias,mCategoris);
                recyclerview_home.setLayoutManager(new LinearLayoutManager(getActivity()));

            // add divider//

//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview_home.getContext(),LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
                recyclerview_home.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

                ///////////////////////////////////////////////
                recyclerview_home.setAdapter(adapter);

        return v;





    }

    private void initImageBitmap(){
        mImages.add("https://genknews.genkcdn.vn/2018/12/10/3260739408bd888d2dc9b611f35af672-1544414276519470629002.jpg");
        mNames.add("Vì một vụ tai nạn máy bay ... bắt đầu từ hôm nay, chúng tôi dành cả mùa xuân của cuộc đời mình trên mùa xuân của cuộc đời mình trên");
        mStatus.add("Chap20ádawdad");
        mTacgias.add("Anonimus,jahdiuahwdui,hadgiuawghd,ahdiuaw");
        mCategoris.add("Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,");

        mImages.add("https://genknews.genkcdn.vn/2018/9/5/92db14c7bd721721cdf430b6c6cb5dd5-15361276186361415434208.jpg");
        mNames.add("truyen 2");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");

        mImages.add("https://i.pinimg.com/originals/8d/5a/ec/8d5aecb67421acb3b3364e5342151560.jpg");
        mNames.add("truyen 3");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");
        mImages.add("https://genknews.genkcdn.vn/2018/12/10/3260739408bd888d2dc9b611f35af672-1544414276519470629002.jpg");
        mNames.add("Vì một vụ tai nạn máy bay ... bắt đầu từ hôm nay, chúng tôi dành cả mùa xuân của cuộc đời mình trên mùa xuân của cuộc đời mình trên");
        mStatus.add("Chap20ádawdad");
        mTacgias.add("Anonimus,jahdiuahwdui,hadgiuawghd,ahdiuaw");
        mCategoris.add("Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,");

        mImages.add("https://genknews.genkcdn.vn/2018/9/5/92db14c7bd721721cdf430b6c6cb5dd5-15361276186361415434208.jpg");
        mNames.add("truyen 2");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");

        mImages.add("https://i.pinimg.com/originals/8d/5a/ec/8d5aecb67421acb3b3364e5342151560.jpg");
        mNames.add("truyen 3");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");
        mImages.add("https://genknews.genkcdn.vn/2018/12/10/3260739408bd888d2dc9b611f35af672-1544414276519470629002.jpg");
        mNames.add("Vì một vụ tai nạn máy bay ... bắt đầu từ hôm nay, chúng tôi dành cả mùa xuân của cuộc đời mình trên mùa xuân của cuộc đời mình trên");
        mStatus.add("Chap20ádawdad");
        mTacgias.add("Anonimus,jahdiuahwdui,hadgiuawghd,ahdiuaw");
        mCategoris.add("Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,");

        mImages.add("https://genknews.genkcdn.vn/2018/9/5/92db14c7bd721721cdf430b6c6cb5dd5-15361276186361415434208.jpg");
        mNames.add("truyen 2");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");

        mImages.add("https://i.pinimg.com/originals/8d/5a/ec/8d5aecb67421acb3b3364e5342151560.jpg");
        mNames.add("truyen 3");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");
        mImages.add("https://genknews.genkcdn.vn/2018/12/10/3260739408bd888d2dc9b611f35af672-1544414276519470629002.jpg");
        mNames.add("Vì một vụ tai nạn máy bay ... bắt đầu từ hôm nay, chúng tôi dành cả mùa xuân của cuộc đời mình trên mùa xuân của cuộc đời mình trên");
        mStatus.add("Chap20ádawdad");
        mTacgias.add("Anonimus,jahdiuahwdui,hadgiuawghd,ahdiuaw");
        mCategoris.add("Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,Romance,Commedi,");

        mImages.add("https://genknews.genkcdn.vn/2018/9/5/92db14c7bd721721cdf430b6c6cb5dd5-15361276186361415434208.jpg");
        mNames.add("truyen 2");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");

        mImages.add("https://i.pinimg.com/originals/8d/5a/ec/8d5aecb67421acb3b3364e5342151560.jpg");
        mNames.add("truyen 3");
        mStatus.add("Chap20");
        mTacgias.add("Anonimus");
        mCategoris.add("Romance");
    }

    private void initRecyclerview(){
        RecyclerView recyclerView;
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
}
