# Study
20.11.24(Tue)

-Error : Cannot be added to the orderList (orderList.html & OrderController)

=>Troubleshooting : Contrary to my opinion, the problem was with the OrderService. 

@Transactional(readOnly = true) All Settings


Omit this
@Transactional
    public Long order(Long memberId, Long itemId, int count)




20.11.23(Mon)

-JPA1 function implementation 93/93

-Error : Cannot be added to the orderList (orderList.html & OrderController)

