package service

import com.example.ms_course_ingress.dao.entity.PaymentEntity
import com.example.ms_course_ingress.dao.repository.PaymentRepository
import com.example.ms_course_ingress.exception.NotFoundException
import com.example.ms_course_ingress.model.enums.PaymentStatus
import com.example.ms_course_ingress.model.request.CreatePaymentRequest
import com.example.ms_course_ingress.service.abstraction.PaymentService
import com.example.ms_course_ingress.service.concrete.PaymentServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static com.example.ms_course_ingress.mapper.PaymentMapper.PAYMENT_MAPPER

class PaymentServiceHandlerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    PaymentService paymentService

    PaymentRepository paymentRepository

    def setup() {
        paymentRepository = Mock()
        paymentService = new PaymentServiceHandler(paymentRepository)
    }

    def "TestCreatePayment"() {

        given:
        def request = random.nextObject(CreatePaymentRequest)
        def entity = PAYMENT_MAPPER.buildPaymentEntity(request)

        when:
        paymentService.createPayment(request)

        then:
        1 * paymentRepository.save(entity)
    }

    def "TestGetPayment success"(){
        given:
        def id = random.nextLong()
        def entity = random.nextObject(PaymentEntity)
        def expected = PAYMENT_MAPPER.buildPaymentResponse(entity)


        when:
        def actual = paymentService.getPayment(id)

        then:
        1 * paymentRepository.findByIdAndStatusNot(id, PaymentStatus.DELETED) >> Optional.of(entity)
        actual.id == expected.id
        actual.amount == expected.amount
        actual.description == expected.description
        actual.status == expected.status
    }

    def "TestGetPayment error payment not found" (){
        given:
        def id = random.nextLong()

        when:
        paymentService.getPayment(id)

        then:
        1 * paymentRepository.findByIdAndStatusNot(id, PaymentStatus.DELETED)>> Optional.empty()
        thrown(NotFoundException)
//        NotFoundException ex = thrown()
//        ex.message == "PAYMENT_NOT_FOUND"
    }

    def "TestUpdatePaymentDescription success"(){
        given:
            def id = random.nextLong()
            def description = random.nextObject(String)
            def payment = random.nextObject(PaymentEntity)

        when:
            paymentService.updatePaymentDescription(id, description)

        then:
            1 * paymentRepository.findByIdAndStatusNot(id, PaymentStatus.DELETED)>> Optional.of(payment)
            1 * paymentRepository.save(payment)
            payment.description == description
    }

    def "TestUpdatePaymentDescription payment not found error"(){
        given:
        def id = random.nextLong()
        def description = random.nextObject(String)

        when:
        paymentService.updatePaymentDescription(id, description)
        then:

        1 * paymentRepository.findByIdAndStatusNot(id,PaymentStatus.DELETED)>>Optional.empty()
        0 * paymentRepository.save(_)
        thrown(NotFoundException)

//        NotFoundException ex= thrown()
//        ex.message == "PAYMENT_NOT_FOUND"
    }

    def "TestDeletePayment"(){
        given:
        def id = random.nextLong()
        def payment = random.nextObject(PaymentEntity)

        when:
        paymentService.deletePayment(id)

        then:
        1 * paymentRepository.findByIdAndStatusNot(id,PaymentStatus.DELETED)>>Optional.of(payment)
        1 * paymentRepository.save(payment)
    }
}
