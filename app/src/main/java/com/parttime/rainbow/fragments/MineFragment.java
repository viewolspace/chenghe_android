package com.parttime.rainbow.fragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.parttime.base.base.BaseFragment;
import com.parttime.base.bean.LoginBean;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.UserInfoUtil;
import com.parttime.rainbow.R;
import com.parttime.rainbow.activity.AboutUsActivity;
import com.parttime.rainbow.activity.JoinPartTimeActivity;
import com.parttime.rainbow.activity.LoginActivity;
import com.parttime.rainbow.activity.PersonalResumeActivity;
import com.parttime.rainbow.view.dilaog.ChangeNickNameDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.rl_my_resume)
    RelativeLayout rl_my_resume;
    @BindView(R.id.rl_my_apply)
    RelativeLayout rl_my_apply;
    @BindView(R.id.rl_change_nick_name)
    RelativeLayout rl_change_nick_name;
    @BindView(R.id.rl_about_us)
    RelativeLayout rl_about_us;
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;

    private LoginBean.UserInfo userInfo;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View root) {
        initView();

    }

    private void initView(){
        userInfo = UserInfoUtil.getInstance().getUserInfo();
        if (userInfo !=null&& userInfo.getUserId()!=0){
            tv_nickname.setText(userInfo.getNickName());

            Glide.with(iv_avatar).load(userInfo.getHeadPic())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    //.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)) 强制加载图片
                    .into(iv_avatar);
        }else {
            tv_nickname.setText("点击登陆");
            Glide.with(iv_avatar).load(R.drawable.default_avatar)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(iv_avatar);
        }
    }
    @Override
    protected void initData() {

    }
    @OnClick({R.id.rl_my_resume,R.id.rl_my_apply,R.id.rl_change_nick_name,R.id.rl_about_us,R.id.tv_nickname})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_my_resume:
                if (UserInfoUtil.getInstance().isLogin()){
                    PersonalResumeActivity.start(getContext());
                }else {
                    LoginActivity.start(getContext());
                }

                break;
            case R.id.rl_my_apply:
                if (UserInfoUtil.getInstance().isLogin()){
                    JoinPartTimeActivity.start(getContext());
                }else {
                    LoginActivity.start(getContext());
                }
                break;
            case R.id.rl_change_nick_name:
                if (UserInfoUtil.getInstance().isLogin()){
                    ChangeNickNameDialog changeNickNameDialog = new ChangeNickNameDialog(getContext());
                    changeNickNameDialog.show();
                }else {
                    LoginActivity.start(getContext());
                }
                break;
            case R.id.rl_about_us:
                AboutUsActivity.start(getContext());
                break;
            case R.id.tv_nickname:
                if (!UserInfoUtil.getInstance().isLogin()){
                    LoginActivity.start(getContext());
                }
                break;
        }
    }
    @Override
    public void handleDefaultEvent(RxEvent event) {
        if (event.getEventType() == RxEvent.EventType.USERINFO_UPDATE){
            initView();
        }

    }





}
