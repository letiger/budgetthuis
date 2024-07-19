package nl.budgetthuis.compose.androidapp.domain.usecase

import android.net.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nl.budgetthuis.compose.androidapp.common.Constants.DEFAULT_ERROR_MESSAGE
import nl.budgetthuis.compose.androidapp.common.DispatchersProvider
import nl.budgetthuis.compose.androidapp.common.Resource
import nl.budgetthuis.compose.androidapp.data.remote.dto.toCoinDetail
import nl.budgetthuis.compose.androidapp.domain.model.CoinDetail
import nl.budgetthuis.compose.androidapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository,
    private val dispatchersProvider: DispatchersProvider
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetail = repository.getCoinByDetail(coinId).toCoinDetail()
            emit(Resource.Success(coinDetail))
        } catch (httpException: HttpException) {
            emit(
                Resource.Error(message = httpException.localizedMessage ?: DEFAULT_ERROR_MESSAGE)
            )
        } catch (ioException: IOException) {
            emit(
                Resource.Error(message = ioException.localizedMessage ?: DEFAULT_ERROR_MESSAGE)
            )
        }
    }.flowOn(dispatchersProvider.io)
}