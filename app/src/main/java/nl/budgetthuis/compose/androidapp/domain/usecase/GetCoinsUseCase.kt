package nl.budgetthuis.compose.androidapp.domain.usecase

import android.net.http.HttpException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nl.budgetthuis.compose.androidapp.common.Constants.DEFAULT_ERROR_MESSAGE
import nl.budgetthuis.compose.androidapp.common.DispatchersProvider
import nl.budgetthuis.compose.androidapp.common.Resource
import nl.budgetthuis.compose.androidapp.data.remote.dto.toCoin
import nl.budgetthuis.compose.androidapp.domain.model.Coin
import nl.budgetthuis.compose.androidapp.domain.repository.CoinRepository
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (httpException: HttpException) {
            emit(
                Resource.Error(message = httpException.localizedMessage ?: DEFAULT_ERROR_MESSAGE)
            )
        } catch (ioException: IOException) {
            emit(
                Resource.Error(message = ioException.localizedMessage ?: DEFAULT_ERROR_MESSAGE)
            )
        }
    }
}