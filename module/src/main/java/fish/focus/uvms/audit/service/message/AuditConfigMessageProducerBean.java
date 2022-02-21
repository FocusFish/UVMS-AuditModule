/*
﻿Developed with the contribution of the European Commission - Directorate General for Maritime Affairs and Fisheries
© European Union, 2015-2016.

This file is part of the Integrated Fisheries Data Management (IFDM) Suite. The IFDM Suite is free software: you can
redistribute it and/or modify it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or any later version. The IFDM Suite is distributed in
the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a
copy of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.
 */
package fish.focus.uvms.audit.service.message;

import fish.focus.uvms.commons.message.api.MessageConstants;
import fish.focus.uvms.commons.message.impl.AbstractProducer;
import eu.europa.ec.fisheries.uvms.config.exception.ConfigMessageException;
import eu.europa.ec.fisheries.uvms.config.message.ConfigMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;

@Stateless
@LocalBean
public class AuditConfigMessageProducerBean extends AbstractProducer implements ConfigMessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(AuditConfigMessageProducerBean.class);

    @Resource(mappedName = "java:/" + MessageConstants.QUEUE_CONFIG)
    private Queue destination;

    @Resource(mappedName = "java:/" + MessageConstants.QUEUE_AUDIT)
    private Queue replyToQueue;

    @Override
    public Destination getDestination() {
        return destination;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String sendConfigMessage(String text) throws ConfigMessageException {
        try {
            return sendModuleMessage(text, replyToQueue);
        } catch (JMSException e) {
            LOG.error("[ Error when sending config message. ] {}", e.getMessage());
            throw new ConfigMessageException("[ Error when sending config message. ]");
        }
    }
}