package useless.dagger2;

import dagger.Component;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/8/13 09:48
 * @describe ：
 */
@ActivityScope
@Component(modules = CommonModule.class)
public interface CommonComponent {
    void inject(Dagger2Activity activity);
}
