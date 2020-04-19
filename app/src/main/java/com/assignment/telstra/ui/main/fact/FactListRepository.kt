package com.assignment.telstra.ui.main.fact


import androidx.lifecycle.MutableLiveData
import com.assignment.telstra.core.store.online.models.FactModel
import com.assignment.telstra.core.store.online.services.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

open class FactListRepository(val apiService: ApiService) {

    var responseMutableLiveData = MutableLiveData<FactModel?>()
    var disposable = CompositeDisposable()

    fun getFactsList(): MutableLiveData<FactModel?> {
        val responseObservable = apiService.getEntity()
        disposable.add(
            responseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<FactModel>>() {
                    override fun onSuccess(t: Response<FactModel>) {
                        try {
                            responseMutableLiveData.postValue(t.body())
                        } catch (e: Exception) {
                            e.printStackTrace()
                            responseMutableLiveData.postValue(null)
                        }
                    }

                    override fun onError(e: Throwable) {
                        responseMutableLiveData.postValue(null)
                    }
                })
        )
        return responseMutableLiveData
    }
}