using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterviewQuestion
{
    class Queue : IQueuable
    {
        const int CAPACITY = 10;
        int size;
        int headIndex;
        int endIndex;
        String[] array;

        public Queue()
        {
            array = new String[CAPACITY];
            size = 0;
            headIndex = 0;
            endIndex = -1;
        }

        public String[] Enqueue(String value)
        {
            if(size>=CAPACITY)
            {
                Console.WriteLine("The queue is full. Please remove some element!");
                return null;
            }
            array[++endIndex] = value;
            
            size++;

            return array;
        }

        public String Dequeue()
        {
            if(size == 0)
            {
                Console.WriteLine("The queue is empty!");
                return "";
            }

            String tmpStr = array[headIndex];
            for (int i = headIndex+1; i <= endIndex;i++ )
            {
                array[i - 1] = array[i];
            }
            size--;
            endIndex--;


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
