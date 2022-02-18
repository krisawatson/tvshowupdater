@XmlSchema(
        namespace = "https://showrss.info",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix="", namespaceURI="https://showrss.info"),
                @XmlNs(prefix="tv", namespaceURI="https://showrss.info")
        }
)
package com.kricko.tvshowupdater.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;