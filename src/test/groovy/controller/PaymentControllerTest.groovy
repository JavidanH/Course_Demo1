package controller

import com.example.ms_course_ingress.controller.PaymentController
import com.example.ms_course_ingress.model.request.CreatePaymentRequest
import com.example.ms_course_ingress.service.abstraction.PaymentService
import com.example.ms_course_ingress.service.concrete.PaymentServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class PaymentControllerTest extends Specification {

    PaymentService paymentService
    PaymentController paymentController
    MockMvc mockMvc

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def setup() {
        paymentService = Mock(PaymentServiceHandler)
        paymentController = new PaymentController(paymentService)

        mockMvc = standaloneSetup(paymentController)
                .build()
    }

    def "Test create payment success"() {
        given:
        def amount = random.nextInt()
        def description = random.nextObject(String)
        def request = CreatePaymentRequest.builder()
                .amount(amount)
                .description(description)
                .build()

        def requestJson = """
        {
        "amount": $amount,
        "description": "$description" 
    }
    """
        when:
        def result = mockMvc.perform(
                post("/v1/payments")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson)
        )


        then:
        1 * paymentService.createPayment(request)
        result.andExpect(status().isCreated())

    }

}
