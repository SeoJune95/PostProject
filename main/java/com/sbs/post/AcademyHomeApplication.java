package com.sbs.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication		// 이게 붙어 있어야 메인 클래스로 인식함. 스프링부트의 자동설정이나, 스프링 Bean의 읽기 및 생성을 모두 자동으로 설정.
//	항상 최상단에 있어야함.
// springapplication.run 이 자동 실행 되면서 외장 WAS를 실행합니다. 스프링 부트는 내장 WAS가 있어서 내장 WAS 사용을 권장합니다.
public class AcademyHomeApplication {
	public static void main(String[] args) {
		SpringApplication.run(AcademyHomeApplication.class, args);
	}

}

// src - main - resources - templates : 안에는 템플릿 엔진들이 들어있음. ex) web, mustache, lombok 등.
// src - main - webapp : jsp 파일 경로..? jsp는 jar 로는 사용 불가하고 war 에서만 동작함.



// TODO : spring boot의 특징
/*
*  *starter* : 의존관계 즉, dependency 를 간단하게 정의해주는 모듈.
*  빌드 도구(나는 그리들로했음) : 버전 해결, 개발을 호율화하는 플러그인 이다.
*  구성 클래스 : XML이 아닌 애너테이션과 자바로 설정을 작성합니다.
*  자동 구성 : 디폴트 구성이 적용되며 필요한 부분만 설정하면 됩니다.
*  메인 class : 자바 명령으로 내장된 톰켓을 실행. (명령을 통해 터미널에서도 실행 할수 있고 다른 방법이 있음.)
*  설정 파일 : 속성을 외부 파일에 정의 할 수 있습니다. 그리고 동작 사양을 쉽게 변경할 수 있습니다.
 */

// gradle 버전 확인방법 : gradle - gradle-wrapper.properties 안에서 distributionUrl 부분 확인.

// annotation 찾아보자~

// FramWork란? Libraries 와의 차이점은?
// - 프레임워크는 뼈대나 기반 구조를 뜻함. IOC 기술이 적용된 대표적인게 프레임워크임.프로그래머가 완성시키는 작업을 해야함.
// 라이브러리는 활용할 수 있는 도구들의 집합.
// 프레임워크와 라이브러리의 차이점은 어플리 케이션의 흐름을 누가 쥐고있느냐의 차이. 프레임워크는 전체적인 흐름을 쥐고있고 사용자가 그안에서 필요한 코드를 넣음.
// 라이브러리는 사용자가 전체적인 흐름을 만들며 라이브러리가 가져다 쓰는것.



// TODO : 정적 페이지
// 스프링 부트에선 정적 템플릿을 지원함. 실시간으로 변화가 없는 데이터.?
// src/main/resources/static 에 html 파일을 생성.