# Java 기반 그림판 프로그램

이 프로젝트는 Java Swing을 사용하여 구현한 간단한 그림판 프로그램입니다. 사용자는 다양한 도형을 그리고, 색상을 선택하며, 텍스트를 추가할 수 있습니다.

## 주요 기능

1. 그리기 기능
   - 선, 사각형, 타원 그리기
   - 채워진 사각형, 타원 그리기
   - 텍스트 추가

2. 색상 선택
   - 기본 색상 선택 (빨강, 파랑, 노랑, 초록, 검정, 흰색)
   - 사용자 정의 색상 선택

3. 폰트 설정
   - 폰트 종류, 스타일, 크기 선택

4. 파일 관리
   - 새 파일 생성
   - 저장 및 불러오기

5. 편집 기능
   - 우클릭으로 도형 삭제

## 사용 방법

1. 프로그램 실행 후 상단 메뉴에서 원하는 도구 선택
2. 캔버스 영역에서 마우스로 드래그하여 도형 그리기
3. 텍스트 입력 시 원하는 위치 클릭 후 다이얼로그에 텍스트 입력
4. 우클릭으로 가장 가까운 도형 삭제 가능
5. 파일 메뉴에서 저장 및 불러오기 기능 사용

## 개발 환경

- 언어: Java
- GUI 프레임워크: Java Swing
- 개발 도구: (사용한 IDE 정보 추가)

## 프로젝트 구조

- `Shape.java`: 그림 요소의 기본 정보 클래스
- `AddLable.java`: 텍스트 입력 다이얼로그 클래스
- `SelectFont.java`: 폰트 선택 다이얼로그 클래스
- `DrawPicture.java`: 메인 그림판 클래스

## 실행 방법

1. 소스 코드를 컴파일합니다.
2. `DrawPicture` 클래스의 `main` 메서드를 실행합니다.

## 향후 개선 사항

- 실행 취소/다시 실행 기능 추가
- 레이어 기능 구현
- 이미지 삽입 기능 추가
- 사용자 인터페이스 개선
<br/>
<br/>

# Java Paint Program

This project is a simple drawing board program implemented using Java Swing. Users can draw different shapes, select colors, and add text.

## Key Features

1. Drawing Features
- Draw Line, Square, Ellipse
- Draw a filled square, ellipse
- Add Text

2. Select Color
- Select default color (red, blue, yellow, green, black, white)
- Select a custom color

3. Font Settings
- Select font type, style, and size

4. File Management
- Create a new file
- Save and import

5. Edit Features
- Right-click to delete a figure

## How to use it

1. Select the desired tool from the top menu after running the program
2. Draw a figure by dragging with your mouse in the canvas area
3. When entering text, click the desired location and enter text into the dialog
4. Right-click to delete the nearest figure
5. Enable save and import capabilities from the File menu

## development environment

- Language: Java
- GUI Framework: Java Swing
- Development tools: (Add IDE information used)

## Project Structure

- 'Shape.java': basic information class for picture elements
- 'AddLable.java': text input dialog class
- 'Select Font.java': font selection dialog class
- 'DrawPicture.java': Main Paint Class

## How to execute

1. Compiles the source code.
2. Run the 'main' method of the 'DrawPicture' class.

## Future Improvements

- Add undone/re-enable capabilities
- Implementing Layer Features
- Add Image Insertion
- Improve user interface


