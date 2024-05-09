package service;

import com.example.ms_course_ingress.service.concrete.MathService;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import spock.lang.Specification
import spock.lang.Unroll;

class MathServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    MathService mathService;

    def setup(){
        mathService = new MathService()
    }
@Unroll
    def "TestSum" (){
        given :
        def firstData = a
        def secondData = b

        when:
        def actual = mathService.sum(firstData,secondData)

        then:
        actual == expected

        where:
        a | b | expected
        5 | 3 | 8
        -5 | 3 | -2
        2 | 0 | 2
        -5 | -3 | -8
    }
}
