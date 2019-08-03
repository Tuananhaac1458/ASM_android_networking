package com.example.asm_android_networking.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asm_android_networking.Adapter.Recyclerview_adapter;
import com.example.asm_android_networking.Background_readJson;
import com.example.asm_android_networking.InforMangaActivity;
import com.example.asm_android_networking.R;
import com.example.asm_android_networking.Readjson;
import com.example.asm_android_networking.Repository.Manga;


import org.json.JSONArray;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment implements Recyclerview_adapter.onClicklistener {
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

    private RecyclerView recyclerview_home;
    Recyclerview_adapter adapter;
    private Manga manga;
    private ArrayList<Manga> mangas = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    Socket msSocket;
    {
        try{
            msSocket = IO.socket("http://192.168.0.103:3000/");
        }catch (URISyntaxException e){
            Log.d( "aaaa","asd");
            throw new RuntimeException( e );
        }
    }

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
//        msSocket.connect();
//        msSocket.emit("getAll");

    }


Emitter.Listener onGetall = new Emitter.Listener() {
    @Override
    public void call(Object... args) {
        JSONArray jsonArray = (JSONArray) args[0];
        Log.d("asd", String.valueOf(jsonArray));
        new Background_readJson(getContext(),jsonArray,recyclerview_home).execute();

    }
};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
               v = inflater.inflate(R.layout.fragment_fragment__home, container, false);
                recyclerview_home = v.findViewById(R.id.recyclerview_home);
                adapter = new Recyclerview_adapter(getContext(),mangas,this);
                recyclerview_home.setLayoutManager(new LinearLayoutManager(getActivity()));

            // add divider//

//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerview_home.getContext(),LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
                recyclerview_home.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                ///////////////////////////////////////////////
                recyclerview_home.setAdapter(adapter);
      //  msSocket.on("getAll",onGetall);
        return v;





    }

    private void initImageBitmap(){
        String[] categori = {"Hanh dong","Tinhcam,Hentai"};



    for (int i =0 ; i<9;i++){
        manga = new Manga("123","https://avt.mkklcdnv3.com/avatar_225/21970-ut918329.jpg","Bạn Trai Tôi Là Lính Cứu Hỏa Dâm Dục",5,"Vodanh",categori);
        mangas.add(manga);
    }
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

    @Override
    public void OnTypeClick(int position, boolean typeClick) {
       Manga mangass = mangas.get(position);
        if (typeClick){
            Toast.makeText(getActivity(), "Long click: "+ mangass.getId(), Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getActivity(), InforMangaActivity.class);
            intent.putExtra("Name",mangass.getName());
            startActivity(intent);
        }
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
