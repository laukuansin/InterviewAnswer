using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterviewQuestion
{
    public class Stack : IQueuable
    {
        const int CAPACITY = 10;
        int size;
        int currentIndex;
        String[] array;

        public Stack()
        {
            array = new String[CAPACITY];
            size = 0;
            currentIndex = -1;
        }

        public String[] Enqueue(String value)
        {
            if(size>=CAPACITY)
            {
                Console.WriteLine("The stack is full. Please remove some element!");
                return null;
            }
            array[++currentIndex] = value;
            size++;
            return array;
        }

        public String Dequeue()
        {
            if(currentIndex==-1)
            {
                Console.WriteLine("The stack is empty!");
                return "";
            }
            String tmpStr = array[currentIndex--];
            size--;
            return tmpStr;
        }

        public String[] getQueue()
        {
            return array;
        }

        public int getSize()
        {
            return size;
        }

    }
}
