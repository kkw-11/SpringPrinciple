package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private DiscountPolicy discountPolicy;
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        /*
        단일 책임 원칙을 잘 지킨 설계, OrderService는 진짜 주문과 관련된 것만 신경쓰고, 할인정책은 discountPolicy에게 위임,
        할인에 대한 정책이 변경되면 OrderService는 변경할 필요없고, discountPolicy만 변경하면 된다.
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

