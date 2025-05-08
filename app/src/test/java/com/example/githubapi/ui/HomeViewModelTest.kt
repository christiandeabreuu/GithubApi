import androidx.paging.PagingData
import androidx.paging.map
import com.example.githubapi.data.model.GitHubRepo
import com.example.githubapi.data.model.Owner
import com.example.githubapi.domain.RepoGitHubUseCase
import com.example.githubapi.ui.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val useCase = mockk<RepoGitHubUseCase>()

    @Before
    fun setup() {
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `repositories should expose correct data`() = runBlocking {
        val mockPagingData = PagingData.from(listOf(
            GitHubRepo(1, "Repo1", 100, 50, Owner("owner1", "https://avatar.com/owner1")),
            GitHubRepo(2, "Repo2", 200, 75, Owner("owner2", "https://avatar.com/owner2"))
        ))

        coEvery { useCase.execute() } returns flowOf(mockPagingData) // Simulamos o fluxo do UseCase

        val result = viewModel.repositories.first().collectData() // Extrai os itens de `PagingData`
        val expected = mockPagingData.collectData() // Extrai os itens esperados

        assertEquals(expected, result) //  verifica os dados são iguais
    }

    // Função auxiliar para coletar os itens reais dentro do `PagingData`
    private suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
        val list = mutableListOf<T>()
        this.map { list.add(it) }
        return list
    }
}
