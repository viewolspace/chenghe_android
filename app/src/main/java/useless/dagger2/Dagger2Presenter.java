package useless.dagger2;



import com.baihe.base.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/8/13 10:03
 * @describe ：
 */
public class Dagger2Presenter extends BasePresenter<Dagger2Contract.View> implements Dagger2Contract.Presenter{
    Dagger2Contract.View mView;
    @Inject
    public Dagger2Presenter(Dagger2Contract.View view) {
        super(view);
        mView = view;
    }

    @Override
    protected void onDestroy() {

    }

    @Override
    public void test() {
        mView.start();
    }
}
