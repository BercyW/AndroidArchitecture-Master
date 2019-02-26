package com.example.c052735.simpleexamplemvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.c052735.simpleexamplemvvm.BasicApp;
import com.example.c052735.simpleexamplemvvm.DataRepository;
import com.example.c052735.simpleexamplemvvm.db.entity.CommentEntity;
import com.example.c052735.simpleexamplemvvm.db.entity.ProductEntity;

import java.util.List;

/**
 * public class User{
 *     public mName;
 *     Observable mObservable;
 *
 *      public User(String name){
 *          mName = name;
 *      }
 *
 *     public String getName() {
 *         return name;
 *     }
 *
 *     public setName(String name){
 *         boolean isSame = TextUtil.equals(mName,name);
 *         this.mName = name;
 *         if(!isSame && mObservable !=null) {
 *             mObservable.onNameChanged(name);
 *         }
 *     }
 *     public void setObservable(Observable observer) {
 *         mObservable = observer;
 *     }
 *
 *     public interface Observable{
 *         onNameChanged(String newName);
 *     }
 * }
 *
 *
 * User user = new User("Joe");
 * user.setObservable(new User.Observable() {
 *     @override
 *     public void onNameChanged(String newName){
 *         Log.i("user new name = :",newName);
 *     }
 * });
 *user.setName("Doe");
 *
 */
public class ProductViewModel extends AndroidViewModel {

    public final int mProductId;
    private final LiveData<ProductEntity> mObservableProduct;
    private final LiveData<List<CommentEntity>> mObservableComment;

    /**
     * wrap the field can be observable
     */
    public ObservableField<ProductEntity> product = new ObservableField<>();

    public ProductViewModel(@NonNull Application application,DataRepository repository, final int productId) {
        super(application);
        mProductId = productId;
        mObservableProduct = repository.loadProduct(mProductId);
        mObservableComment = repository.loadComments(mProductId);
    }

    /**
     * Expose the livedata comments query so the UI can observe it.
     */
    public LiveData<List<CommentEntity>> getComments(){
        return mObservableComment;
    }
    public LiveData<ProductEntity> getObservableProduct() {
        return mObservableProduct;
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }
    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProductViewModel(mApplication,mRepository,mProductId);
        }
    }


}
