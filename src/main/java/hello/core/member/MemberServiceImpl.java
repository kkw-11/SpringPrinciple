package hello.core.member;

public class MemberServiceImpl implements MemberService{

    /*
    문제점
     MemberRepository 라는 역할에 의존하고 있지만 추가로 MemoryMemberRepository 라는 구체화에도 의존 -> DIP 의존관계 역전원칙 위배
     게다가, MemoryMemberRepository는 나중에 DB가 확정 되면 변경해야하는데 이때 OCP 원칙 위배
    */
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
