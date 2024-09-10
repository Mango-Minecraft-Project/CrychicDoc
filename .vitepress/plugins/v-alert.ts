import { container } from "@mdit/plugin-container";
import { logger } from '../config/sidebarControl';
import type { PluginSimple } from "markdown-it";

export const v_alert: PluginSimple = (md) => {
    const type = ["v-success", "v-info", "v-warning", "v-error"]
    type.forEach((name) =>
        md.use((md) =>
            container(md, {
                name,
                openRender: (tokens, index, _options) => {
                    const info: string = tokens[index].info.trim().slice(name.length).trim();
                    const defaultTitle: string = name.replace("v-", "")
                    return `<p><v-alert class="v-alert" title="${info || defaultTitle}" type="${defaultTitle}" >\n`;
                },
                closeRender: (): string => {
                    return `</v-alert></p>\n`
                },
            }),
        )
        
    );
};