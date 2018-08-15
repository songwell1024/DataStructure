package linkedList;

/**
 * @author WilsonSong
 * @date 2018/8/15/015
 */
public class listNodeSolution {
    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val){
            head = head.next;
        }
        if(head == null){
            return null;
        }
        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val){
                ListNode node = prev.next;
                prev.next = node.next;
                node.next = null;
            }else{
                prev = prev.next;
            }
        }
        return head;
    }

   class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

    public ListNode removeElements2(ListNode head, int val) {
       if (head == null){
           return null;
       }
       ListNode res = removeElements(head.next,val);
       if (head.val == val){
           return res;
       }else {
           head.next = res;
           return head;
       }

    }
}
