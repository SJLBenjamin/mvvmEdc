package com.endoc.mvvmedc.ui.fragment.monitor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.endoc.mvvmedc.bridge.state.CaseListFragmentViewModel;
import com.endoc.mvvmedc.data.room.entity.User;
import com.endoc.mvvmedc.databinding.FragmentCaseListBinding;
import com.endoc.mvvmedc.databinding.RecycleUserListLayoutBinding;
import com.endoc.mvvmedc.ui.adapter.BaseBindingAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentCaseListBinding mFragmentCaseListBinding;
    private CaseListFragmentViewModel mCaseListFragmentViewModel;

    public CaseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseListFragment newInstance(String param1, String param2) {
        CaseListFragment fragment = new CaseListFragment();
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
        //viewModel
        mCaseListFragmentViewModel = getAppViewModelProvider().get(CaseListFragmentViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_case_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleView(View.VISIBLE,View.VISIBLE,View.INVISIBLE,View.VISIBLE,getString(R.string.cases),getString(R.string.add_cases),View.GONE);
        mFragmentCaseListBinding = DataBindingUtil.bind(view);
        mFragmentCaseListBinding.setLifecycleOwner(this);
        mFragmentCaseListBinding.setVm(mCaseListFragmentViewModel);


        final BaseBindingAdapter baseBindingAdapter = new BaseBindingAdapter<User, RecycleUserListLayoutBinding>(mActivity) {
            //布局文件
            @Override
            protected int getLayoutResId(int viewType) {
                return R.layout.recycle_user_list_layout;
            }

            @Override
            protected void onBindItem(RecycleUserListLayoutBinding binding, User item, RecyclerView.ViewHolder holder) {
               //binding.setVm(item)如果User是Obsevable或者liveData类型,那么直接绑定就可以生效了
                Logger.d("列表界面名字=="+item.name);
                binding.tvName.setText(item.name);
            }
        };

        mFragmentCaseListBinding.rcUserList.setLayoutManager(new LinearLayoutManager(mActivity));
        mFragmentCaseListBinding.rcUserList.setAdapter(baseBindingAdapter);

        //数据库user表观察者
        mCaseListFragmentViewModel.getUsers(mActivity).observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Logger.d("列表界面=="+users.size());
                baseBindingAdapter.setList(users);
                baseBindingAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void right() {
        super.right();
        Logger.d("CaseListFragment");
    }

    @Override
    public void backChanged(Boolean s) {
        super.backChanged(s);
        Logger.d("caseListFragment backClick");
    }

    @Override
    public <T> void rightChanged(T s) {
        super.rightChanged(s);
        if(s.equals(mActivity.getString(R.string.add_cases))){
            Logger.d("CaseListFragment rightChanged");
           getNavController().navigate(R.id.action_caseListFragment_to_addCaseFragment);
        }
    }


}
