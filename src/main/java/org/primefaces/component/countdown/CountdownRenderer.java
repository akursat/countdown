/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.component.countdown;

import java.io.IOException;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.WidgetBuilder;

/**
 *
 * @author akursat
 * @author batuhancikrikci
 */
public class CountdownRenderer extends CoreRenderer {

    @Override
    public void decode(FacesContext context, UIComponent component) {
        Countdown countdown = (Countdown) component;

    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        Countdown countdown = (Countdown) component;

        encodeMarkup(context, countdown);
        encodeScript(context, countdown);
    }

    protected void encodeMarkup(FacesContext context, Countdown countdown) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("span", countdown);
        writer.writeAttribute("id", countdown.getClientId(), null);
        writer.writeAttribute("class", Countdown.STYLE_CLASS, null);
        writer.endElement("span");

    }

    protected void encodeScript(FacesContext context, Countdown countdown) throws IOException {
        String clientId = countdown.getClientId(context);
        WidgetBuilder wb = getWidgetBuilder(context);
        String finishTime = countdown.getFinishTime();
        Locale locale = countdown.calculateLocale(context);

        wb.initWithDomReady("Countdown", countdown.resolveWidgetVar(), clientId);
        wb.attr("pattern", countdown.getPattern(), null)
                .attr("reverse", countdown.isReverse())
                .attr("autoStart", countdown.isAutoStart())
                .attr("locale", locale.toString())
                .attr("finishTime", finishTime);

        if (countdown.isReverse() && finishTime.equals("infinite")) {
            throw new UnsupportedOperationException(countdown.getClass() + " not supported operation:" + " reverse:" + countdown.isReverse() + " & " + "finisTime:" + finishTime);
        }

        encodeClientBehaviors(context, countdown);

        wb.finish();


    }
}
