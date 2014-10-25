/**
 * @author kris
 *
 */
@XmlSchema(
    namespace = "showrss",
    elementFormDefault = XmlNsForm.QUALIFIED,
	xmlns={
       @XmlNs(prefix="showrss", namespaceURI="http://showrss.info/")
   }
)

package com.kricko.tvshowupdater.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

