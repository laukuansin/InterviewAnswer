using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterviewQuestion
{
    public interface IQueuable
    {
        String[] Enqueue(String value);
        String Dequeue();
        String[] getQueue();
        int getSize();
    }
}
