package mapper

import com.example.ms_course_ingress.dao.entity.PaymentEntity
import com.example.ms_course_ingress.mapper.PaymentMapper
import com.example.ms_course_ingress.model.enums.PaymentStatus
import com.example.ms_course_ingress.model.request.CreatePaymentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class PaymentMapperTest extends Specification {

    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildPaymentEntity"() {
        given:
        def request = random.nextObject(CreatePaymentRequest)

        when:
        def entity = PaymentMapper.PAYMENT_MAPPER.buildPaymentEntity(request)

        then:
        entity.amount == request.amount
        entity.description == request.description
        entity.status == PaymentStatus.DRAFT
    }

    def "TestBuildPaymentResponse"() {

        given:
        def entity = random.nextObject(PaymentEntity)

        when:
        def response = PaymentMapper.PAYMENT_MAPPER.buildPaymentResponse(entity)

        then:
        response.id == entity.id
        response.amount == entity.amount
        response.description == entity.description
        response.status == entity.status

    }
}
