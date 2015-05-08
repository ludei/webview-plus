package com.ludei.chromium;

import java.lang.reflect.Field;

import android.util.Log;

public class ResourceProvider {
    private static boolean sInitialized;

    static void registerResources() {
        if (sInitialized) {
            return;
        }
        
     // attr
        org.chromium.content.R.attr.select_dialog_multichoice =
                R.attr.select_dialog_multichoice;
        org.chromium.content.R.attr.select_dialog_singlechoice =
                R.attr.select_dialog_singlechoice;
        // color
        org.chromium.ui.R.color.dropdown_dark_divider_color =
                R.color.dropdown_dark_divider_color;
        org.chromium.ui.R.color.dropdown_divider_color =
                R.color.dropdown_divider_color;
        org.chromium.ui.R.color.color_picker_border_color =
                R.color.color_picker_border_color;
        // dimen
        org.chromium.content.R.dimen.link_preview_overlay_radius =
                R.dimen.link_preview_overlay_radius;
        org.chromium.ui.R.dimen.dropdown_item_height =
                R.dimen.dropdown_item_height;
        org.chromium.ui.R.dimen.dropdown_item_divider_height =
                R.dimen.dropdown_item_divider_height;
        org.chromium.ui.R.dimen.color_button_height =
                R.dimen.color_button_height;
        org.chromium.ui.R.dimen.config_min_scaling_span =
                R.dimen.config_min_scaling_span;
        org.chromium.ui.R.dimen.config_min_scaling_touch_major =
                R.dimen.config_min_scaling_touch_major;
        // drawable
        org.chromium.content.R.drawable.ondemand_overlay =
                R.drawable.ondemand_overlay;
        org.chromium.ui.R.drawable.color_button_background =
                R.drawable.color_button_background;
        org.chromium.ui.R.drawable.color_picker_advanced_select_handle =
                R.drawable.color_picker_advanced_select_handle;
        // id
        org.chromium.ui.R.id.ampm = R.id.ampm;
        org.chromium.ui.R.id.date_picker =
                R.id.date_picker;
        org.chromium.ui.R.id.date_time_suggestion_value =
                R.id.date_time_suggestion_value;
        org.chromium.ui.R.id.date_time_suggestion_label =
                R.id.date_time_suggestion_label;
        org.chromium.ui.R.id.hour = R.id.hour;
        org.chromium.ui.R.id.milli = R.id.milli;
        org.chromium.ui.R.id.minute = R.id.minute;
        org.chromium.ui.R.id.pickers = R.id.pickers;
        org.chromium.ui.R.id.position_in_year =
                R.id.position_in_year;
        org.chromium.ui.R.id.second = R.id.second;
        org.chromium.ui.R.id.second_colon =
                R.id.second_colon;
        org.chromium.ui.R.id.second_dot =
                R.id.second_dot;
        org.chromium.content.R.id.select_action_menu_select_all =
                R.id.select_action_menu_select_all;
        org.chromium.content.R.id.select_action_menu_cut =
                R.id.select_action_menu_cut;
        org.chromium.content.R.id.select_action_menu_copy =
                R.id.select_action_menu_copy;
        org.chromium.content.R.id.select_action_menu_paste =
                R.id.select_action_menu_paste;
        org.chromium.content.R.id.select_action_menu_share =
                R.id.select_action_menu_share;
        org.chromium.content.R.id.select_action_menu_web_search =
                R.id.select_action_menu_web_search;
        org.chromium.ui.R.id.time_picker =
                R.id.time_picker;
        org.chromium.ui.R.id.year = R.id.year;
        org.chromium.content.R.id.main_text =
                R.id.main_text;
        org.chromium.content.R.id.sub_text =
                R.id.sub_text;
        org.chromium.content.R.id.arrow_image =
                R.id.arrow_image;
        org.chromium.ui.R.id.selected_color_view =
                R.id.selected_color_view;
        org.chromium.ui.R.id.title =
                R.id.title;
        org.chromium.ui.R.id.more_colors_button =
                R.id.more_colors_button;
        org.chromium.ui.R.id.color_picker_advanced =
                R.id.color_picker_advanced;
        org.chromium.ui.R.id.color_picker_simple =
                R.id.color_picker_simple;
        org.chromium.ui.R.id.color_button_swatch =
                R.id.color_button_swatch;
        org.chromium.ui.R.id.more_colors_button_border =
                R.id.more_colors_button_border;
        org.chromium.ui.R.id.gradient =
                R.id.gradient;
        org.chromium.ui.R.id.text =
                R.id.text;
        org.chromium.ui.R.id.seek_bar =
                R.id.seek_bar;
        org.chromium.ui.R.id.dropdown_label =
                R.id.dropdown_label;
        org.chromium.ui.R.id.dropdown_popup_window =
                R.id.dropdown_popup_window;
        org.chromium.ui.R.id.dropdown_sublabel =
                R.id.dropdown_sublabel;
        // layout
        org.chromium.ui.R.layout.date_time_picker_dialog =
                R.layout.date_time_picker_dialog;
        org.chromium.ui.R.layout.date_time_suggestion =
                R.layout.date_time_suggestion;
        org.chromium.ui.R.layout.two_field_date_picker =
                R.layout.two_field_date_picker;
        org.chromium.ui.R.layout.multi_field_time_picker_dialog =
                R.layout.multi_field_time_picker_dialog;
        org.chromium.content.R.layout.validation_message_bubble =
                R.layout.validation_message_bubble;
        org.chromium.ui.R.layout.color_picker_dialog_title =
                R.layout.color_picker_dialog_title;
        org.chromium.ui.R.layout.color_picker_dialog_content =
                R.layout.color_picker_dialog_content;
        org.chromium.ui.R.layout.color_picker_advanced_component =
                R.layout.color_picker_advanced_component;
        org.chromium.ui.R.layout.dropdown_item =
                R.layout.dropdown_item;
        // menu
        org.chromium.content.R.menu.select_action_menu =
                R.menu.select_action_menu;
        // string
        org.chromium.content.R.string.accessibility_content_view =
                R.string.accessibility_content_view;
        org.chromium.ui.R.string.accessibility_date_picker_month =
                R.string.accessibility_date_picker_month;
        org.chromium.ui.R.string.accessibility_date_picker_week =
                R.string.accessibility_date_picker_week;
        org.chromium.ui.R.string.accessibility_date_picker_year =
                R.string.accessibility_date_picker_year;
        org.chromium.ui.R.string.accessibility_datetime_picker_date =
                R.string.accessibility_datetime_picker_date;
        org.chromium.ui.R.string.accessibility_datetime_picker_time =
                R.string.accessibility_datetime_picker_time;
        org.chromium.content.R.string.actionbar_share =
                R.string.actionbar_share;
        org.chromium.content.R.string.actionbar_web_search =
                R.string.actionbar_web_search;
        org.chromium.ui.R.string.date_picker_dialog_clear =
                R.string.date_picker_dialog_clear;
        org.chromium.ui.R.string.date_picker_dialog_set =
                R.string.date_picker_dialog_set;
        org.chromium.ui.R.string.date_picker_dialog_title =
                R.string.date_picker_dialog_title;
        org.chromium.ui.R.string.date_picker_dialog_other_button_label =
                R.string.date_picker_dialog_other_button_label;
        org.chromium.ui.R.string.date_time_picker_dialog_title =
                R.string.date_time_picker_dialog_title;
        org.chromium.content.R.string.media_player_error_button =
                R.string.media_player_error_button;
        org.chromium.content.R.string.media_player_error_text_invalid_progressive_playback =
                R.string.media_player_error_text_invalid_progressive_playback;
        org.chromium.content.R.string.media_player_error_text_unknown =
                R.string.media_player_error_text_unknown;
        org.chromium.content.R.string.media_player_error_title =
                R.string.media_player_error_title;
        org.chromium.content.R.string.media_player_loading_video =
                R.string.media_player_loading_video;
        org.chromium.ui.R.string.month_picker_dialog_title =
                R.string.month_picker_dialog_title;
        org.chromium.content.R.string.profiler_error_toast =
                R.string.profiler_error_toast;
        org.chromium.content.R.string.profiler_no_storage_toast =
                R.string.profiler_no_storage_toast;
        org.chromium.content.R.string.profiler_started_toast =
                R.string.profiler_started_toast;
        org.chromium.content.R.string.profiler_stopped_toast =
                R.string.profiler_stopped_toast;
        org.chromium.ui.R.string.time_picker_dialog_am =
                R.string.time_picker_dialog_am;
        org.chromium.ui.R.string.time_picker_dialog_pm =
                R.string.time_picker_dialog_pm;
        org.chromium.ui.R.string.time_picker_dialog_hour_minute_separator =
                R.string.time_picker_dialog_hour_minute_separator;
        org.chromium.ui.R.string.time_picker_dialog_hour_minute_separator =
                R.string.time_picker_dialog_hour_minute_separator;
        org.chromium.ui.R.string.time_picker_dialog_minute_second_separator =
                R.string.time_picker_dialog_minute_second_separator;
        org.chromium.ui.R.string.time_picker_dialog_second_subsecond_separator =
                R.string.time_picker_dialog_second_subsecond_separator;
        org.chromium.ui.R.string.time_picker_dialog_title =
                R.string.time_picker_dialog_title;
        org.chromium.ui.R.string.week_picker_dialog_title =
                R.string.week_picker_dialog_title;
        org.chromium.ui.R.string.copy_to_clipboard_failure_message =
                R.string.copy_to_clipboard_failure_message;
        org.chromium.ui.R.string.low_memory_error =
                R.string.low_memory_error;
        org.chromium.ui.R.string.opening_file_error =
                R.string.opening_file_error;
        org.chromium.ui.R.string.color_picker_button_more =
                R.string.color_picker_button_more;
        org.chromium.ui.R.string.color_picker_hue =
                R.string.color_picker_hue;
        org.chromium.ui.R.string.color_picker_saturation =
                R.string.color_picker_saturation;
        org.chromium.ui.R.string.color_picker_value =
                R.string.color_picker_value;
        org.chromium.ui.R.string.color_picker_button_set =
                R.string.color_picker_button_set;
        org.chromium.ui.R.string.color_picker_button_cancel =
                R.string.color_picker_button_cancel;
        org.chromium.ui.R.string.color_picker_dialog_title =
                R.string.color_picker_dialog_title;
        org.chromium.ui.R.string.color_picker_button_red =
                R.string.color_picker_button_red;
        org.chromium.ui.R.string.color_picker_button_cyan =
                R.string.color_picker_button_cyan;
        org.chromium.ui.R.string.color_picker_button_blue =
                R.string.color_picker_button_blue;
        org.chromium.ui.R.string.color_picker_button_green =
                R.string.color_picker_button_green;
        org.chromium.ui.R.string.color_picker_button_magenta =
                R.string.color_picker_button_magenta;
        org.chromium.ui.R.string.color_picker_button_yellow =
                R.string.color_picker_button_yellow;
        org.chromium.ui.R.string.color_picker_button_black =
                R.string.color_picker_button_black;
        org.chromium.ui.R.string.color_picker_button_white =
                R.string.color_picker_button_white;
        // style
        org.chromium.content.R.style.SelectPopupDialog =
                R.style.SelectPopupDialog;
        org.chromium.ui.R.style.DropdownPopupWindow =
                R.style.DropdownPopupWindow;

        // Resources needed by android_webview/
        //AwResource.RAW_LOAD_ERROR = R.raw.loaderror;
        //AwResource.RAW_NO_DOMAIN = R.raw.nodomain;
        //AwResource.STRING_DEFAULT_TEXT_ENCODING =
        //        R.string.default_text_encoding;

        //if (Build.IS_DEBUGGABLE) {
        if (false) {
            // Ensure that we aren't missing any resource mappings.
            verifyFields(org.chromium.content.R.class);
            verifyFields(org.chromium.ui.R.class);
        }

        // Resources needed by android_webview/

        /*AwResource.RAW_LOAD_ERROR = R.raw.loaderror;
        AwResource.RAW_NO_DOMAIN = R.raw.nodomain;

        AwResource.STRING_DEFAULT_TEXT_ENCODING =
                R.string.default_text_encoding; */

        sInitialized = true;
    }

    // Verify that all the fields of the inner classes of |R| have a valid mapping.
    // This ensures that if a resource is added upstream, we won't miss providing
    // a mapping downstream.
    private static void verifyFields(Class<?> R) {
        for (Class<?> c : R.getDeclaredClasses()) {
            verifyFields(c);  // recursively check inner classes.
        }

        for (Field f : R.getDeclaredFields()) {
            try {
                if (f.getInt(null) == 0) {
                    throw new RuntimeException("Missing resource mapping for " +
                            R.getName() + "." + f.getName());
                }
            } catch (IllegalAccessException e) { }
        }
    }
}
