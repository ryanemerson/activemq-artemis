/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.artemis.core.protocol.openwire.amq;

import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;

public interface AMQDeadLetterStrategy
{

   /**
    * Allow pluggable strategy for deciding if message should be sent to a dead letter queue
    * for example, you might not want to ignore expired or non-persistent messages
    * @param message
    * @return true if message should be sent to a dead letter queue
    */
   boolean isSendToDeadLetterQueue(Message message);

   /**
    * Returns the dead letter queue for the given message and subscription.
    */
   ActiveMQDestination getDeadLetterQueueFor(Message message, AMQSubscription subscription);

   /**
    * @return true if processes expired messages
    */
   boolean isProcessExpired();

   /**
    * @param processExpired the processExpired to set
    */
   void setProcessExpired(boolean processExpired);

   /**
    * @return the processNonPersistent
    */
   boolean isProcessNonPersistent();

   /**
    * @param processNonPersistent the processNonPersistent to set
    */
   void setProcessNonPersistent(boolean processNonPersistent);

   boolean isDLQ(ActiveMQDestination destination);

   /**
    * Allows for a Message that was already processed by a DLQ to be rolled back in case
    * of a move or a retry of that message, otherwise the Message would be considered a
    * duplicate if this strategy is doing Message Auditing.
    *
    * @param message
    */
   void rollback(Message message);

}
