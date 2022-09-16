using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterviewQuestion
{
    class Program
    {
        static void Main(string[] args)
        {
            Factory factory = new Factory();
            /*
            IQueuable stack = factory.getQueue("Stack");
            stack.Enqueue("Lion");
            stack.Enqueue("Tiger");
            stack.Enqueue("Cat");
            stack.Enqueue("Dog");
            stack.Enqueue("Pig");
            stack.Enqueue("Apple");
            stack.Enqueue("Orange");
            stack.Enqueue("Green");
            stack.Enqueue("Blue");
            stack.Enqueue("Gold");
            stack.Dequeue();
            stack.Dequeue();
            stack.Enqueue("Bitcoin");
            stack.Enqueue("Jump");
            

            
            Console.WriteLine(stack.getSize());*/
            
            IQueuable queue = factory.getQueue("Queue");
            queue.Enqueue("Lau");
            queue.Enqueue("Kuan");
            queue.Enqueue("Sin");
            queue.Enqueue("Kuan1");
            queue.Enqueue("Sin1"); 
            queue.Enqueue("Kuan2");
            queue.Enqueue("Sin2");
            queue.Enqueue("Kuan3");
            queue.Enqueue("Sin3");
            queue.Enqueue("Kuan4");
            queue.Enqueue("Sin4");

            Console.WriteLine(queue.getSize());
            String tmp = queue.Dequeue();
            Console.WriteLine(tmp);

            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());
            
            Console.ReadKey();
        }
    }
}
