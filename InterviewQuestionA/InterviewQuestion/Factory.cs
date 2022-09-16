using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterviewQuestion
{
    class Factory
    {
        public IQueuable getQueue(String queueType)
        {
            if(queueType == null)
            {
                return null;
            }
            else if(queueType.Equals("Queue"))
            {
                return new Queue();
            }
            else if(queueType.Equals("Stack"))
            {
                return new Stack();
            }
            return null;
        }
    }
}
