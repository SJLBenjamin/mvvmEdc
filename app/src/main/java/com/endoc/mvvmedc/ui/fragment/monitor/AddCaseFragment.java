package com.endoc.mvvmedc.ui.fragment.monitor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.endoc.mvvmedc.bridge.state.AddCaseFragmentViewModel;
import com.endoc.mvvmedc.data.room.entity.User;
import com.endoc.mvvmedc.databinding.FragmentAddCaseBinding;
import com.endoc.mvvmedc.ui.adapter.UserImforAdapter;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCaseFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AddCaseFragmentViewModel mAddCaseFragmentViewModel;

    public AddCaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCaseFragment newInstance(String param1, String param2) {
        AddCaseFragment fragment = new AddCaseFragment();
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
        mAddCaseFragmentViewModel = getAppViewModelProvider().get(AddCaseFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initTitleView(View.VISIBLE,View.VISIBLE,View.INVISIBLE,View.VISIBLE,getString(R.string.add_cases),getString(R.string.save),View.GONE);
        return inflater.inflate(R.layout.fragment_add_case, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentAddCaseBinding fragmentAddCaseBinding =DataBindingUtil.bind(view);
        fragmentAddCaseBinding.setVm(mAddCaseFragmentViewModel);
        fragmentAddCaseBinding.setClick(new ProxyClick());
        fragmentAddCaseBinding.setLifecycleOwner(this);
        //初始化设置user
        mAddCaseFragmentViewModel.getUserObservableField().setValue(mUser);

       /* RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_add_cases);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new UserImforAdapter(mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));*/


        mAddCaseFragmentViewModel.getUsers(mActivity).observe(mActivity, new Observer<List<User>>() {
            @Override
            public void onChanged(final List<User> users) {
                        Logger.d("更改后的===="+users.size());
            }
        });

    }

    @Override
    public <T> void rightChanged(T s) {
        super.rightChanged(s);
        if(s.equals(mActivity.getString(R.string.save))){
            //先这样写
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Logger.d("mUser==="+mUser.toString());
                    mUser.id=mUser.telephone;
                   Logger.d("long===="+ mAddCaseFragmentViewModel.saveUser(mActivity,mUser));
                    //Logger.d("删除的行数==="+mAddCaseFragmentViewModel.deleteUserAll(mActivity));
                }
            }).start();

        }
    }

    User mUser = new User();

    public class ProxyClick{

        public void sexSelect(){
            final List<String> sexList = new ArrayList<String>();
            sexList.add(getString(R.string.man));
            sexList.add(getString(R.string.woman));
            OptionsPickerView pvOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    mUser.sex=sexList.get(options1);
                    mAddCaseFragmentViewModel.userObservableField.setValue(mUser);
                }
            }).setTitleText(getString(R.string.sex)).build();
            pvOptions.setPicker(sexList);
            pvOptions.show();
        }

        public void birthdaySelect(){
            Calendar mStartDate = Calendar.getInstance();
            Calendar mSelectDate = Calendar.getInstance();
            Calendar mEndDate = Calendar.getInstance();
            mStartDate.set(1900, 0, 1);
            mSelectDate.set(1960, 0, 1);
            TimePickerView pvStartTime = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    mUser.birthDay=date.getTime();
                    mAddCaseFragmentViewModel.userObservableField.setValue(mUser);
                }
            }).setType(new boolean[]{true, true, true, false, false, false}).setRangDate(mStartDate, mEndDate).setDate(mSelectDate).setTitleText(getString(R.string.birthday)).build();// 默认全部显示.build();
            pvStartTime.show();
        }
    }


}
