package com.ramadan.androidtemplate.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ramadan.test_utils.TestContextProvider
import com.ramadan.test_utils.TestCoroutineRule
import com.ramadan.test_utils.mock
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.internal.runners.statements.ExpectException
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import java.lang.Error

class DemoViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var demoViewModel: DemoViewModel

    @Mock
    private lateinit var demoUsecase: DemoUsecase

    @Mock
    private var viewStateObserver: Observer<DemoViewState> = mock()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        demoViewModel = DemoViewModel(demoUsecase, TestContextProvider())
            .apply { getStateLiveData().observeForever(viewStateObserver) }
    }

    @Test
    fun `DemoViewModel is ready for test`() {
        assertNotNull(demoViewModel)
    }

    @Test
    fun `should success when getData returns proper data`(){
        testCoroutineRule.runBlockingTest {
            //Given
            val data = "hello"
            `when`(demoUsecase.getData()).thenReturn(data)
            //When
            demoViewModel.getData()
            //Then
            verify(viewStateObserver).onChanged(DemoViewState.Loading)
            verify(viewStateObserver).onChanged(DemoViewState.Success(data))
        }
    }

    @Test(expected = Throwable::class)
    fun `should fail when getData throws exception`(){
       testCoroutineRule.runBlockingTest {
           //Given
           val error = Error()
           `when`(demoUsecase.getData()).thenThrow(error)
           //When
           demoViewModel.getData()
           //Then
           verify(viewStateObserver).onChanged(DemoViewState.Loading)
           verify(viewStateObserver).onChanged(DemoViewState.Error(error))
       }
    }

}