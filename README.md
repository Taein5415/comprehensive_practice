# 자판기 프로그램 (Vending Machine)

## 📌 소개
이 프로젝트는 Java로 구현한 자판기 시뮬레이션 프로그램입니다.  
사용자는 돈을 투입하고 원하는 음료를 선택하여 구매할 수 있으며, 거스름돈도 반환받을 수 있습니다.

## 🚀 기능
- 사용자가 돈을 투입하여 자판기에서 음료를 구매 가능, 거스름돈을 반환 가능하다.
- 음료 종류 및 재고 관리, 화폐 관리. 판매 내역 조회(구현중)


## ✨고려한 점
1. UserCLI와 UserCLI 모두 Service를 통해 CashRepository와 DrinkRepository에서 데이터를 가져온다. 이때, 데이터의 무결성을 위반할 수 있으므로 Repository 클래스의 생성자에 싱글톤 패턴을 적용했다.
<details>
<summary>CashRepository 생성자</summary>
<div markdown="1">
<img src="./image/CashRepository_singleton.png">
</div>
</details>
<details>
<summary>CashRepository 생성자</summary>
<div markdown="1">
<img src="./image/DrinkRepository_singleton.png">
</div>
</details>
