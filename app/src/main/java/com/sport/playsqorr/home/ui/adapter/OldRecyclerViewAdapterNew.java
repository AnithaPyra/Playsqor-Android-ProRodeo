package com.sport.playsqorr.home.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sport.playsqorr.R;
import com.sport.playsqorr.fragments.HomeFrag;
import com.sport.playsqorr.pojos.MyCardsPojo;
import com.sport.playsqorr.utilities.Utilities;
import com.sport.playsqorr.views.Dashboard;
import com.sport.playsqorr.views.HomeCards_MoreScreen;
import com.sport.playsqorr.views.MatchupScreen;
import com.sport.playsqorr.views.MatchupScreen_PlayAPick;
import com.sport.playsqorr.views.Matchup_WinPlayGoTimeTwo;
import com.sport.playsqorr.views.PlayPickGo_MatchupScreen;
import com.sport.playsqorr.views.PlayWithCash;
import com.sport.playsqorr.views.TvFullScreenVideo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
/*


public class OldRecyclerViewAdapterNew
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //implements Filterable {
    // A menu item view type.
    private static final int MENU_ITEM_VIEW_TYPE = 0;

    // The banner ad view type.
    private static final int BANNER_AD_VIEW_TYPE = 1;

    // An Activity's Context.
    private final Context context;

    // The list of banner ads and menu items.
    private final List<Object> recyclerViewItems;
    private List<Object> recyclerViewItems_NewFliter;

    // FragmentActivity ;

    //handle header data
    String headerStr;
//        MyCardsPojo all_home_cards;
//        int ss;
//        String leagueName;

    OldRecyclerViewAdapterNew(List<Object> recyclerViewItems, Context context) {
        //    this.context = context;
        this.recyclerViewItems = recyclerViewItems;
        this.context = context;
        this.recyclerViewItems_NewFliter = recyclerViewItems;
//            setHasStableIds(true);
    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }

    */
/**
     * Determines the view type for the given position.
     *//*

    @Override
    public int getItemViewType(int position) {
        int viewType = -1;

        if (recyclerViewItems.get(position) instanceof MyCardsPojo) {
            viewType = 0;
        } else {
            viewType = 1;
        }
        return viewType;
    }

    */
/**
     * Creates a new view for a menu item view or a banner ad view
     * based on the viewType. This method is invoked by the layout manager.
     *//*

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:
                View menuItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.fragment_mlb_new, viewGroup, false);
                return new OldRecyclerViewAdapterNew.MenuItemViewHolder(menuItemLayoutView);
            case BANNER_AD_VIEW_TYPE:
                // fall through
//                    View bannerLayoutView = LayoutInflater.from(
//                            viewGroup.getContext()).inflate(R.layout.header_layout,
//                            viewGroup, false);
//                    return new AdViewHolder(bannerLayoutView);
            default:
                View bannerLayoutView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.header_layout,
                        viewGroup, false);
                return new OldRecyclerViewAdapterNew.AdViewHolder(bannerLayoutView);
        }
    }

    */
/**
     * Replaces the content in the views that make up the menu item view and the
     * banner ad view. This method is invoked by the layout manager.
     *//*

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int ele_position) {

        int viewType = getItemViewType(ele_position);
        System.out.println("jira homefragment viewType "+viewType+" posiiton "+ele_position);
        final int position = holder.getAdapterPosition();
//            holder.setIsRecyclable(false);


        if (recyclerViewItems.get(ele_position) instanceof String) {
            System.out.println("jira homefragment viewType iinstance of string");

            headerStr = (String) recyclerViewItems.get(ele_position);

        }

        Log.e("3243----k", headerStr + " postion");


//            if (recyclerViewItems.get(ele_position) instanceof MyCardsPojo) {
//
//
//
//            }


        switch (viewType) {
            case MENU_ITEM_VIEW_TYPE:

                final OldRecyclerViewAdapterNew.MenuItemViewHolder listingView = (OldRecyclerViewAdapterNew.MenuItemViewHolder) holder;

                final MyCardsPojo all_home_cards = (MyCardsPojo) recyclerViewItems.get(ele_position);
                //      final int ss = all_home_cards.getPlayerCardIds().size();
                final String leagueName = all_home_cards.getLeagueAbbrevation();

                listingView.tvCardTitle.setText(all_home_cards.getCardTitle());


                String cardType = all_home_cards.getMatchupType();
                listingView.tvMatchUpType.setText(cardType);

                if (cardType.equalsIgnoreCase("match-up")) {
                    //    listingView.tvMatchUpType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_matchup, 0, 0, 0);
                    listingView.ivMatchUp.setVisibility(View.VISIBLE);
                    listingView.ivOverUnder.setVisibility(View.GONE);
                    listingView.tvPlus.setVisibility(View.GONE);
                } else if (cardType.equalsIgnoreCase("mixed")) {
                    // listingView.tvMatchUpType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_ptt, 0, 0, 0);
                    listingView.ivMatchUp.setVisibility(View.VISIBLE);
                    listingView.ivOverUnder.setVisibility(View.VISIBLE);
                    listingView.tvPlus.setVisibility(View.VISIBLE);
                } else if (cardType.equalsIgnoreCase("OVER-UNDER")) {
                    //listingView.tvMatchUpType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_overunder, 0, 0, 0);
                    listingView.ivMatchUp.setVisibility(View.GONE);
                    listingView.ivOverUnder.setVisibility(View.VISIBLE);
                    listingView.tvPlus.setVisibility(View.GONE);
                }

//                    if (all_home_cards.getIsLive().equalsIgnoreCase("true")) {
//                        listingView.tvStartTime.setVisibility(View.GONE);
//                        listingView.llLive.setVisibility(View.VISIBLE);
//                    } else {
//                        listingView.tvStartTime.setVisibility(View.VISIBLE);
//                        listingView.llLive.setVisibility(View.GONE);
//                    }

                if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.nfl_lg_id))) {

                    listingView.head_img_icon.setImageResource(R.drawable.ic_am_football);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.nba_lg_id))) {

                    listingView.head_img_icon.setImageResource(R.drawable.ic_basketball);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.nhl_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_hockey);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.nascar_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_nascar_h);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.mlb_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_baseball);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.epl_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_tennis);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.pro_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.rodeo);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.LALIGA_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_am_football);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.mls_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_soccer);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.NCAAMB_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_basketball);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.pga_lg_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_golf);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.nccfb_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_am_football);
                } else if (all_home_cards.getLeagueId().equals(context.getResources().getString(R.string.wildcard_id))) {
                    listingView.head_img_icon.setImageResource(R.drawable.ic_wildcard);
                } else {
                    listingView.head_img_icon.setImageResource(R.drawable.rodeo);

                }

                final String leagueTime = all_home_cards.getStartTime();

                if (leagueTime != null && !leagueTime.equals("")) {
                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                        listingView.tvStartTime.setText(formatTimeDiff);
                        listingView.tvStartTime.setVisibility(View.VISIBLE);
                    } else {
                        listingView.tvStartTime.setVisibility(View.GONE);
                    }
                } else {
                    listingView.tvStartTime.setVisibility(View.GONE);
                }

                Log.e("3333----------", leagueName);
                listingView.player1Img.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_placeholder));
                listingView.player2Img.setImageDrawable(context.getResources().getDrawable(R.drawable.profile_placeholder));
//                    switch (leagueName) {
//                        case "NFL":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.foot_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_football));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_football));
//
//                            break;
//
//                        case "NBA":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.basket_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_basketball));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_basketball));
//
//                            break;
//
//                        case "NHL":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.hockey_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_hockey));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_hockey));
//
//                            break;
//
//                        case "NASCAR":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.car_race_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_nascar));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_nascar));
//
//                            break;
//
//                        case "MLB":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.base_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_baseball));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_baseball));
//
//                            break;
//
//                        case "EPL":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.tennis_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_tennis));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_tennis));
//                            break;
//                        case "PRO RODEO":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.gray));
////                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_tennis));
////                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_tennis));
//                            break;
//
//                        case "LA-LIGA":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.foot_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_football));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_football));
//
//                            break;
//
//                        case "MLS":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.soccer_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_soccer));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_soccer));
//
//                            break;
//
//                        case "NCAAMB":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.basket_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_basketball));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_basketball));
//
//                            break;
//
//                        case "NCAAFB":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.foot_ball_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_football));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_football));
//
//                            break;
//
//                        case "PGA":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.golf_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_golf));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_golf));
//                            break;
//
//                        case "WILD CARD":
//                            listingView.cardColor.setBackgroundColor(getResources().getColor(R.color.golf_color));
//                            listingView.player1Img.setImageDrawable(getResources().getDrawable(R.drawable.cl_golf));
//                            listingView.player2Img.setImageDrawable(getResources().getDrawable(R.drawable.cr_golf));
//                            break;
//
//                        default:
//                            break;
//                    }
//

                //       Log.e("Kalyan--count", "header--" + headerStr + "  title  " + all_home_cards.getCardTitle() + "  " + ss);


                if (headerStr.equalsIgnoreCase("My cards")) {
                    listingView.cardColor.setVisibility(View.VISIBLE);
                    listingView.stas_count.setVisibility(View.VISIBLE);
                    listingView.tvCardTitleCount.setVisibility(View.VISIBLE);

                    //      Log.e("Kalyan--count", "" + all_home_cards.getCardTitle() + " count: mycards " + ss);

*/
/*

                        if (ss >= 2) {

                            Log.e("Kalyan--count", "" + all_home_cards.getCardTitle() + " count: >2  " + ss);
//                            all_home_cards.setCout_data(true);//
//            switch (myCardsList.getLeagueAbbrevation()) {


                            switch (leagueName) {
                                case "NFL":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.foot_ball_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
//                                    all_home_cards.setCout_data(true);
                                    break;

                                case "NBA":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.basket_ball_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
//                                    all_home_cards.setCout_data(true);
                                    break;

                                case "NHL":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.hockey_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
//                                    all_home_cards.setCout_data(true);
                                    break;

                                case "NASCAR":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.car_race_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "MLB":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.base_ball_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "EPL":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.tennis_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;
                                case "PRORODEO":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.tennis_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "LA-LIGA":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.foot_ball_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "MLS":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.soccer_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "NCAAMB":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.basket_ball_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "PGA":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.golf_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "WILD CARD":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.golf_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;

                                case "NCAAFB":
                                    listingView.tvCardTitleCount.setBackgroundColor(getResources().getColor(R.color.golf_color));
                                    listingView.tvCardTitleCount.setText(all_home_cards.getPlayerCardIds().size() + "");
                                    // all_home_cards.setCout_data(true);
                                    break;
                                default:

                                    break;
                            }


                        } else {

                            Log.e("Kalyan--count", "" + all_home_cards.getCardTitle() + " count: scroll " + ss);
                            listingView.tvCardTitleCount.setVisibility(View.INVISIBLE);
                            // all_home_cards.setCout_data(false);
                        }
*//*



                } else {
                    listingView.tvCardTitleCount.setVisibility(View.INVISIBLE);
                    // all_home_cards.setCout_data(false);
                }


                if (all_home_cards.getPlayerImageLeft() != null && !all_home_cards.getPlayerImageLeft().equals("")) {
                    Picasso.with(getActivity())
                            .load(all_home_cards.getPlayerImageLeft())
                            .into(new Target() {

                                @Override
                                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                                        listingView.player1Img.setImageBitmap(convertTParellelogram(bitmap, "xxx", getActivity()));
                                    listingView.player1Img.setImageBitmap(bitmap);
                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {
                                }

                                @Override
                                public void onBitmapFailed(Drawable errorDrawable) {
                                }
                            });

                }
                if (all_home_cards.getPlayerImageRight() != null && !all_home_cards.getPlayerImageRight().equals("")) {
                    Picasso.with(getActivity())
                            .load(all_home_cards.getPlayerImageRight())
                            .into(new Target() {
                                @Override
                                public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
//                                        listingView.player2Img.setImageBitmap(convertTParellelogram(bitmap, "pare", getContext()));
                                    listingView.player2Img.setImageBitmap(bitmap);
                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {
                                }

                                @Override
                                public void onBitmapFailed(Drawable errorDrawable) {
                                }
                            });

                }


                if (headerStr.equalsIgnoreCase("My cards")) {

                    //     Toast.makeText(context, headerStr + "--1894--", Toast.LENGTH_LONG).show();

                    listingView.llTotal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (all_home_cards.getPlayerCardIds().size() >= 2) {
                                Log.e("size---", "--1904---l");
//                                Intent matchup = new Intent(context, MyCards_MoreScreen.class);
//                                    Intent matchup = new Intent(context, MoreCardsScreen.class);

                                if (all_home_cards.getCardType().trim().equalsIgnoreCase("PLAY TAC TOE")) {
                                    Intent matchup_play = new Intent(context, HomeCards_MoreScreen.class);
                                    matchup_play.putExtra("morecards_info", all_home_cards);
                                    matchup_play.putExtra("cardid", all_home_cards.getCardId());
                                    matchup_play.putExtra("home", "1");
                                    matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
                                    matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                    matchup_play.putExtra("place", "home");
                                    matchup_play.putExtra("cardid_date", "");
                                    matchup_play.putExtra("playerid_m", "");
                                    context.startActivity(matchup_play);
                                } else if (all_home_cards.getCardType().trim().equalsIgnoreCase("WIN PLAY SHOW")) {
//                                        // WIN PLAY SHOW New have to create
//                                        Intent matchup_play = new Intent(context, HomeCards_MoreScreen.class);
//                                        matchup_play.putExtra("morecards_info", all_home_cards);
//                                        matchup_play.putExtra("cardid", all_home_cards.getCardId());
//                                        matchup_play.putExtra("home", "1");
//                                        matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
//                                        matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
//                                        matchup_play.putExtra("place", "home");
//                                        matchup_play.putExtra("cardid_date", "");
//                                        matchup_play.putExtra("playerid_m", "");
//                                        context.startActivity(matchup_play);
                                } else {
                                    Intent matchup = new Intent(context, HomeCards_MoreScreen.class);
                                    matchup.putExtra("morecards_info", all_home_cards);
                                    matchup.putExtra("cardid", all_home_cards.getCardId());
                                    matchup.putExtra("home", "1");
                                    matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                    matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                    matchup.putExtra("place", "home");
                                    matchup.putExtra("cardid_date", "");
                                    matchup.putExtra("playerid_m", "");
                                    context.startActivity(matchup);

                                }


//
                            } else {


                                if (all_home_cards.getCardType().equalsIgnoreCase("PLAY TAC TOE")) {

                                    Intent matchup_play = new Intent(context, PlayPickGo_MatchupScreen.class);
                                    matchup_play.putExtra("home", "1");
                                    matchup_play.putExtra("cardid", all_home_cards.getCardId());
                                    matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
                                    matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                    matchup_play.putExtra("place", "homecards");
                                    matchup_play.putExtra("position_data", true);

                                    matchup_play.putExtra("playerid_m", "");
                                    if (leagueTime != null && !leagueTime.equals("")) {
                                        String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                        if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                            listingView.tvStartTime.setText(formatTimeDiff);
                                            matchup_play.putExtra("cardid_date", formatTimeDiff);
                                        }
                                    } else {
                                        matchup_play.putExtra("cardid_date", "");

                                    }
                                    context.startActivity(matchup_play);
                                } else if (all_home_cards.getCardType().equalsIgnoreCase("WIN PLAY SHOW")) {
//
//                                        Intent matchup_play = new Intent(context, Matchup_NewWinScreen.class);
//                                        matchup_play.putExtra("home", "1");
//                                        matchup_play.putExtra("cardid", all_home_cards.getCardId());
//                                        matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
//                                        matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
//                                        matchup_play.putExtra("place", "homecards");
//                                        matchup_play.putExtra("position_data", true);
//                                        matchup_play.putExtra("playerid_m", "");
//                                        if (leagueTime != null && !leagueTime.equals("")) {
//                                            String formatTimeDiff = getTimeDiff(leagueTime);
//                                            if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
//                                                listingView.tvStartTime.setText(formatTimeDiff);
//                                                matchup_play.putExtra("cardid_date", formatTimeDiff);
//                                            }
//                                        } else {
//                                            matchup_play.putExtra("cardid_date", "");
//
//                                        }
//                                        context.startActivity(matchup_play);
                                } else if (all_home_cards.getCardType().equalsIgnoreCase("PLAY A PICK")) {
//
//
                                    Log.e("size---", "--1918---l");
                                    Intent matchup = new Intent(context, MatchupScreen_PlayAPick.class);
                                    matchup.putExtra("home", "1");
                                    matchup.putExtra("cardid", all_home_cards.getCardId());
                                    matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                    matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                    matchup.putExtra("place", "homecards");
                                    matchup.putExtra("position_data", false);
                                    matchup.putExtra("card_type", all_home_cards.getCardType());
                                    matchup.putExtra("playerid_m", "");
                                    if (leagueTime != null && !leagueTime.equals("")) {
                                        String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                        if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                            listingView.tvStartTime.setText(formatTimeDiff);
                                            matchup.putExtra("cardid_date", formatTimeDiff);
                                        }
                                    } else {
                                        matchup.putExtra("cardid_date", "");

                                    }

                                    context.startActivity(matchup);
                                } else {
                                    Log.e("size---", "--1918---l");
                                    Intent matchup = new Intent(context, MatchupScreen.class);
                                    matchup.putExtra("home", "1");
                                    matchup.putExtra("cardid", all_home_cards.getCardId());
                                    matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                    matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                    matchup.putExtra("place", "homecards");
                                    matchup.putExtra("position_data", true);
                                    matchup.putExtra("card_type", all_home_cards.getCardType());
                                    matchup.putExtra("playerid_m", "");
                                    if (leagueTime != null && !leagueTime.equals("")) {
                                        String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                        if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                            listingView.tvStartTime.setText(formatTimeDiff);
                                            matchup.putExtra("cardid_date", formatTimeDiff);
                                        }
                                    } else {
                                        matchup.putExtra("cardid_date", "");

                                    }

                                    context.startActivity(matchup);
                                }
                            }

                        }
                    });
                } else {


                    // Home Screens
                    Log.e("4------------------", all_home_cards.getCardType());
                    listingView.llTotal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (all_home_cards.getCardType().equalsIgnoreCase("PLAY TAC TOE")) {

                                Intent matchup_play = new Intent(context, PlayPickGo_MatchupScreen.class);
                                matchup_play.putExtra("home", "1");
                                matchup_play.putExtra("cardid", all_home_cards.getCardId());
                                matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
                                matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                matchup_play.putExtra("place", "homecards");
                                matchup_play.putExtra("position_data", false);
                                matchup_play.putExtra("playerid_m", "");
                                if (leagueTime != null && !leagueTime.equals("")) {
                                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                        listingView.tvStartTime.setText(formatTimeDiff);
                                        matchup_play.putExtra("cardid_date", formatTimeDiff);
                                    }
                                } else {
                                    matchup_play.putExtra("cardid_date", "");

                                }
                                context.startActivity(matchup_play);
                            } else if (all_home_cards.getCardType().equalsIgnoreCase("WIN PLAY SHOW")) {

//                                    Intent matchup_play = new Intent(context, Matchup_NewWinScreen.class);
//                                    matchup_play.putExtra("home", "1");
//                                    matchup_play.putExtra("cardid", all_home_cards.getCardId());
//                                    matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
//                                    matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
//                                    matchup_play.putExtra("place", "homecards");
//                                    matchup_play.putExtra("position_data", false);
//                                    matchup_play.putExtra("playerid_m", "");
//                                    if (leagueTime != null && !leagueTime.equals("")) {
//                                        String formatTimeDiff = getTimeDiff(leagueTime);
//                                        if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
//                                            listingView.tvStartTime.setText(formatTimeDiff);
//                                            matchup_play.putExtra("cardid_date", formatTimeDiff);
//                                        }
//                                    } else {
//                                        matchup_play.putExtra("cardid_date", "");
//
//                                    }
//                                    context.startActivity(matchup_play);
                            } else if (all_home_cards.getCardType().equalsIgnoreCase("WIN PLAY SQOR")) {

                                Intent matchup_play = new Intent(context, Matchup_WinPlayGoTimeTwo.class);
                                matchup_play.putExtra("home", "1");
                                matchup_play.putExtra("cardid", all_home_cards.getCardId());
                                matchup_play.putExtra("cardid_title", all_home_cards.getCardTitle());
                                matchup_play.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                matchup_play.putExtra("place", "homecards");
                                matchup_play.putExtra("position_data", false);
                                matchup_play.putExtra("playerid_m", "");
                                matchup_play.putExtra("card_type", all_home_cards.getCardType());
                                if (leagueTime != null && !leagueTime.equals("")) {
                                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                        listingView.tvStartTime.setText(formatTimeDiff);
                                        matchup_play.putExtra("cardid_date", formatTimeDiff);
                                    }
                                } else {
                                    matchup_play.putExtra("cardid_date", "");

                                }
                                context.startActivity(matchup_play);


                            } else if (all_home_cards.getCardType().equalsIgnoreCase("PLAY A PICK")) {
//
//
                                Log.e("size---", "--1918---l");
                                Intent matchup = new Intent(context, MatchupScreen_PlayAPick.class);
                                matchup.putExtra("home", "1");
                                matchup.putExtra("cardid", all_home_cards.getCardId());
                                matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                matchup.putExtra("place", "homecards");
                                matchup.putExtra("position_data", false);
                                matchup.putExtra("card_type", all_home_cards.getCardType());
                                matchup.putExtra("playerid_m", "");
                                if (leagueTime != null && !leagueTime.equals("")) {
                                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                        listingView.tvStartTime.setText(formatTimeDiff);
                                        matchup.putExtra("cardid_date", formatTimeDiff);
                                    }
                                } else {
                                    matchup.putExtra("cardid_date", "");

                                }


                            } else if (all_home_cards.getCardType().equalsIgnoreCase("WIN PLAY GO")) {
//
//
                                Log.e("size---", "--1918---l"+all_home_cards.getCardType());
                                Intent matchup = new Intent(context, Matchup_WinPlayGoTimeTwo.class);
                                matchup.putExtra("home", "1");
                                matchup.putExtra("cardid", all_home_cards.getCardId());
                                matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                matchup.putExtra("place", "homecards");
                                matchup.putExtra("position_data", false);
                                matchup.putExtra("card_type", all_home_cards.getCardType());
                                matchup.putExtra("playerid_m", "");
                                if (leagueTime != null && !leagueTime.equals("")) {
                                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                        listingView.tvStartTime.setText(formatTimeDiff);
                                        matchup.putExtra("cardid_date", formatTimeDiff);
                                    }
                                } else {
                                    matchup.putExtra("cardid_date", "");

                                }

                                context.startActivity(matchup);
                            } else {
                                Log.e("size---", "--1941---l");
                                Intent matchup = new Intent(context, MatchupScreen.class);
                                matchup.putExtra("home", "1");
                                matchup.putExtra("cardid", all_home_cards.getCardId());
                                matchup.putExtra("cardid_title", all_home_cards.getCardTitle());
                                matchup.putExtra("cardid_color1", all_home_cards.getLeagueAbbrevation());
                                matchup.putExtra("place", "homecards");
                                matchup.putExtra("position_data", false);
                                matchup.putExtra("card_type", all_home_cards.getCardType());
                                matchup.putExtra("playerid_m", "");
                                if (leagueTime != null && !leagueTime.equals("")) {
                                    String formatTimeDiff = Utilities.getTimeDiff(leagueTime);
                                    if (formatTimeDiff != null && !formatTimeDiff.equals("")) {
                                        listingView.tvStartTime.setText(formatTimeDiff);
                                        matchup.putExtra("cardid_date", formatTimeDiff);
                                    }
                                } else {
                                    matchup.putExtra("cardid_date", "");

                                }

                                context.startActivity(matchup);
                            }


                        }
                    });
                }


                break;
            case BANNER_AD_VIEW_TYPE:
                // fall through
                final OldRecyclerViewAdapterNew.AdViewHolder adViewHolder = (OldRecyclerViewAdapterNew.AdViewHolder) holder;

                //handle header data
//                    headerStr = "Header";


                Log.e("3243----kk", headerStr);
                Log.e("3243----kk", proRoedoData.size() + "==" + prorodeoCount);
                adViewHolder.tvHeader.setText(headerStr);

                //handle view all
                boolean showViewAll = false;
                adViewHolder.viewSeparator.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
                adViewHolder.viewSeparator.setVisibility(View.VISIBLE);
                //    adViewHolder.rlHeader.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
                switch (headerStr) {
//                        case "My cards":
//                            if (myCardsCount > MY_CARDS_MAX)
//                                showViewAll = true;
//                            adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//                            adViewHolder.viewSeparator.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.extra_light_gray, null));
//                            //       adViewHolder.rlHeader.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.extra_light_gray,null));
//                            adViewHolder.viewSeparator.setVisibility(View.GONE);
//                            adViewHolder.tvHeader_Play.setVisibility(View.GONE);
//
//                            break;
                    case "NFL":
                        if (NFLCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_am_football, 0, 0, 0);

                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        adViewHolder.tvHeader_Play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                    headerPlayUrlNFL("https://www.youtube.com/watch?v=NNZjpoT4z2Q");
                                headerPlayUrlNHL("https://www.youtube.com/watch?v=NNZjpoT4z2Q", "NNZjpoT4z2Q");

                            }
                        });
                        break;

                    case "NBA":
                        if (NBACount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_basketball, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        adViewHolder.tvHeader_Play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                    headerPlayUrl("https://www.youtube.com/watch?v=iwxAvA6vS90");
                                headerPlayUrlNHL("https://www.youtube.com/watch?v=EL-jpyKUH1o", "EL-jpyKUH1o");

                            }
                        });
                        break;

                    case "NHL":
                        if (NHLCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_hockey, 0, 0, 0);

                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        adViewHolder.tvHeader_Play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                headerPlayUrlNHL("https://www.youtube.com/watch?v=y5yWwFbGfF0", "y5yWwFbGfF0");
                            }
                        });
                        break;
                    case "NASCAR":
                        if (NASCARCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_nascar_h, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "MLB":
                        if (MLBCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseball, 0, 0, 0);

                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        adViewHolder.tvHeader_Play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                    headerPlayUrl("https://www.youtube.com/watch?v=iwxAvA6vS90");
                                headerPlayUrlNHL("https://www.youtube.com/watch?v=iwxAvA6vS90", "iwxAvA6vS90");

                            }
                        });
                        break;

                    case "EPL":
                        if (EPLCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tennis, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "PRORODEO":
                        adViewHolder.rlHeader.setVisibility(View.GONE);
//                            rlHeader
                        if (prorodeoCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tennis, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
//                        case "PRO RODEO":
//                            if (prorodeoCount > CARDS_MAX)
//                                showViewAll = true;
//                            adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tennis, 0, 0, 0);
//                            adViewHolder.tvHeader_Play.setVisibility(View.GONE);
//                            break;

                    case "LA-LIGA":
                        if (LALIGACount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_am_football, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "MLS":
                        if (MLSCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_soccer, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "NCAAMB":
                        if (NCAAMBCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_basketball, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "NCAAFB":
                        if (NCAAFBCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_am_football, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "WILDCARD Horse Racing":
                        if (WILDCount_horse > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "WILDCARD Golf Future":

                        Log.e("wwwwwwwwwwwww", "  G:  " + WILDCount_golf + "   " + CARDS_MAX);
                        if (WILDCount_golf > CARDS_MAX) {
                            showViewAll = true;
                        }
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "WILDCARD Football":
                        if (WILDCount_football > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "WILDCARD NFL Draft":
                        if (WILDCount_NFL > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "WILDCARD Baseball Future":
                        if (WILDCount_base > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "WILD CARD":

                        //  adViewHolder.tvHeader.setText("klayan");
*/
/*
                            if (WILDCount_golf > CARDS_MAX) {
                                showViewAll = true;
                                adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                                adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                            } else if (WILDCount_base > CARDS_MAX) {
                                showViewAll = true;
                                adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                                adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                            } else*//*

                        if (WILDCount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;

                    case "PGA":
                        if (PGACount > CARDS_MAX)
                            showViewAll = true;
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_golf, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                    case "Upcoming cards":
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        break;
                    default:
                        adViewHolder.tvHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_wildcard, 0, 0, 0);
                        adViewHolder.tvHeader_Play.setVisibility(View.GONE);
                        break;
                }


                final String finalHeaderStr = headerStr;
                String leagueId = "";

                for (Map.Entry<String, String> entry : leagueData.entrySet()) {
                    if (entry.getKey().equals(finalHeaderStr)) {
                        leagueId = entry.getValue();
                    }
                }
                if (showViewAll) {
                    if (selLeagueId != null && !selLeagueId.equalsIgnoreCase("")) {
                        adViewHolder.tvViewALL.setVisibility(View.GONE);
                    } else {
                        adViewHolder.tvViewALL.setVisibility(View.VISIBLE);
                    }
                } else {
                    adViewHolder.tvViewALL.setVisibility(View.GONE);
                }

                final String finalLeagueId = leagueId;
                adViewHolder.tvViewALL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (finalHeaderStr.equalsIgnoreCase("My cards")) {
//                                try {
//                                    Activity act = getActivity();
//                                    if (act instanceof Dashboard)
//                                        ((Dashboard) act).navToMyCards();
//                                } catch (Exception e) {
//                                    e.getStackTrace();
//                                }
                        } else {

                            //To scroll the view programmatically
                            if (horizontal != null) {
                                // Obtain MotionEvent object
                                long downTime = SystemClock.uptimeMillis();
                                long eventTime = SystemClock.uptimeMillis() + 100;
                                MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 0.0f, 0.0f, 0);
                                switch (finalHeaderStr) {

                                    case "NFL":
                                        horizontal.smoothScrollBy((NFL_pvb.getLeft() - 500), 0);
                                        NFL_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "NBA":
                                        horizontal.smoothScrollBy((NBA_pvb.getLeft() - 500), 0);
                                        NBA_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "NHL":
                                        horizontal.smoothScrollBy((NHL_pvb.getLeft() - 500), 0);
                                        NHL_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "NASCAR":
                                        horizontal.smoothScrollBy((NASCAR_pvb.getLeft() - 500), 0);
                                        NASCAR_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "MLB":
                                        horizontal.smoothScrollBy((mlb_pvb.getLeft() - 500), 0);
                                        mlb_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "EPL":
                                        horizontal.smoothScrollBy((EPL_pvb.getLeft() - 500), 0);
                                        EPL_pvb.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "PRORODEO":
                                        horizontal.smoothScrollBy((pro_pvd.getLeft() - 500), 0);
                                        pro_pvd.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "LA-LIGA":
                                        horizontal.smoothScrollBy((LA_LIGA_pvb.getLeft() - 500), 0);
                                        LA_LIGA_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "MLS":
                                        horizontal.smoothScrollBy((mls_pvb.getLeft() - 500), 0);
                                        mls_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "NCAAMB":
                                        horizontal.smoothScrollBy((NCAAMB_pvb.getLeft() - 500), 0);
                                        NCAAMB_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "NCAAFB":
                                        horizontal.smoothScrollBy((NCAAFB_pvb.getLeft() - 500), 0);
                                        NCAAFB_pvb.dispatchTouchEvent(motionEvent);
                                        break;
//                                        case "WILD CARD":
//                                            horizontal.smoothScrollBy((WILD_pvb.getLeft() - 500), 0);
//                                            WILD_pvb.dispatchTouchEvent(motionEvent);
//                                            break;
                                    case "PGA":
                                        horizontal.smoothScrollBy((PGA_pvb.getLeft() - 500), 0);
                                        PGA_pvb.dispatchTouchEvent(motionEvent);
                                        break;

                                    case "WILDCARD Horse Racing":
                                        horizontal.smoothScrollBy((WILD_pvb_h.getLeft() - 500), 0);
                                        WILD_pvb_h.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "WILDCARD Golf Future":
                                        horizontal.smoothScrollBy((WILD_pvb_g.getLeft() - 500), 0);
                                        WILD_pvb_g.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "WILDCARD Baseball Future":
                                        horizontal.smoothScrollBy((WILD_pvb_b.getLeft() - 500), 0);
                                        WILD_pvb_b.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "WILDCARD NFL Draft":
                                        horizontal.smoothScrollBy((WILD_pvb_n.getLeft() - 500), 0);
                                        WILD_pvb_n.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "WILD CARD":
                                        horizontal.smoothScrollBy((WILD_pvb.getLeft() - 500), 0);
                                        WILD_pvb.dispatchTouchEvent(motionEvent);
                                        break;
                                    case "WILDCARD Football":
                                        horizontal.smoothScrollBy((WILD_pvb_f.getLeft() - 500), 0);
                                        WILD_pvb_f.dispatchTouchEvent(motionEvent);

                                        break;
                                    default:
                                        break;
                                }

                            }
                            selLeagueId = finalLeagueId;
                            selLeagueName = finalHeaderStr;
                            fromRefresh = false;

                            if (bb1.getTag().equals("11")) {
                                HandleIndividualTabs("MATCH-UP");
                            } else {

                                HandleIndividualTabs("PLAY TAC TOE");
                            }
                        }
                    }
                });
            default:
                break;
        }


    }

    class MenuItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView player1Img, player2Img, ivMatchUp, ivOverUnder, head_img_icon;
        LinearLayout llTotal, llLive, stas_count;
        private TextView tvCardTitle, tvMatchUpType, tvStartTime, tvPlus, tvPIds, tvCardTitleCount;
        private View cardColor;

//            private TextView tvHeader, tvViewALL, tvHeader_Play;
//            private View viewSeparator;
//            private RelativeLayout rlHeader;

        MenuItemViewHolder(View convertView) {
            super(convertView);

            tvCardTitle = convertView.findViewById(R.id.tvCardTitle);
            player1Img = convertView.findViewById(R.id.player1Img);
            player2Img = convertView.findViewById(R.id.player2Img);
            head_img_icon = convertView.findViewById(R.id.head_img_icon);
            llTotal = convertView.findViewById(R.id.llTotal);
            tvMatchUpType = convertView.findViewById(R.id.tvMatchUpType);
            tvStartTime = convertView.findViewById(R.id.tvStartTime);
            cardColor = convertView.findViewById(R.id.cardColor);
            llLive = convertView.findViewById(R.id.llLive);
            ivOverUnder = convertView.findViewById(R.id.ivOverUnder);
            ivMatchUp = convertView.findViewById(R.id.ivMatchUp);
            tvPlus = convertView.findViewById(R.id.tvPlus);
            tvPIds = convertView.findViewById(R.id.tvCardids);

            stas_count = convertView.findViewById(R.id.stas_count);
            tvCardTitleCount = convertView.findViewById(R.id.tvCardTitleCount);
            stas_count.setVisibility(View.INVISIBLE);
            tvCardTitleCount.setVisibility(View.INVISIBLE);

//                tvHeader = convertView.findViewById(R.id.tvHeader);
//                tvHeader_Play = convertView.findViewById(R.id.tvHeader_Play);
//                tvViewALL = convertView.findViewById(R.id.tvViewALL);
//                viewSeparator = convertView.findViewById(R.id.viewSeparator);
//                rlHeader = convertView.findViewById(R.id.rlHeader);

        }
    }

    class AdViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHeader, tvViewALL, tvHeader_Play;
        private View viewSeparator;
        private RelativeLayout rlHeader;

        AdViewHolder(View adview) {
            super(adview);
            tvHeader = adview.findViewById(R.id.tvHeader);
            tvHeader_Play = adview.findViewById(R.id.tvHeader_Play);
            tvViewALL = adview.findViewById(R.id.tvViewALL);
            viewSeparator = adview.findViewById(R.id.viewSeparator);
            rlHeader = adview.findViewById(R.id.rlHeader);
        }
    }

    private void headerPlayUrl(String youtubeurl) {
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_promo_redeem1, viewGroup, false);

        Button btnViewAllPromos = dialogView.findViewById(R.id.buttonViewAllPromotions);

        Button btnRedeem = dialogView.findViewById(R.id.btnRedeem);
        ImageView cancel = dialogView.findViewById(R.id.imageViewcancel);

        // dialogView.setBackgroundColor(getResources().getColor(R.color.transparent));


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        alertDialog.setCancelable(false);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //alertDialog.setBackgroundDrawable(Color.TRANSPARENT);
        alertDialog.show();


        btnViewAllPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
               */
/* Fragment PromoFragment = new PromosFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, PromoFragment); // give your fragment container id in first parameter
                fragmentTransaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                fragmentTransaction.commit();*//*

                alertDialog.dismiss();
                Activity act = getActivity();
                if (act instanceof Dashboard)
                    ((Dashboard) act).setPromo();
            }
        });

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PlayWithCash.class);
//                intent.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                startActivity(intent);
            }
        });


        youTubePlayerView = dialogView.findViewById(R.id.youtube_player_view_all);
        youTubePlayerView.getPlayerUiController().showFullscreenButton(false);
        youTubePlayerView.getPlayerUiController().showBufferingProgress(true);
        youTubePlayerView.getPlayerUiController().showYouTubeButton(false);
        youTubePlayerView.getPlayerUiController().showVideoTitle(false);
        youTubePlayerView.getPlayerUiController().showMenuButton(false);
        getLifecycle().addObserver(youTubePlayerView);

        playVideo("", youtubeurl, "dd", "dd", true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }

                alertDialog.dismiss();
                youTubePlayerView.release();
              */
/*  Intent in = new Intent(getActivity(), PlayWithCash.class);
//                in.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                startActivity(in);*//*



            }
        });


    }

    private void headerPlayUrlNFL(String youtubeurl) {
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_promo_redeem1, viewGroup, false);

        Button btnViewAllPromos = dialogView.findViewById(R.id.buttonViewAllPromotions);

        Button btnRedeem = dialogView.findViewById(R.id.btnRedeem);
        ImageView cancel = dialogView.findViewById(R.id.imageViewcancel);

        // dialogView.setBackgroundColor(getResources().getColor(R.color.transparent));


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        alertDialog.setCancelable(false);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //alertDialog.setBackgroundDrawable(Color.TRANSPARENT);
        alertDialog.show();


        btnViewAllPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
               */
/* Fragment PromoFragment = new PromosFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, PromoFragment); // give your fragment container id in first parameter
                fragmentTransaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                fragmentTransaction.commit();*//*

                alertDialog.dismiss();
                Activity act = getActivity();
                if (act instanceof Dashboard)
                    ((Dashboard) act).setPromo();
            }
        });

        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PlayWithCash.class);
//                intent.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                startActivity(intent);
            }
        });

        youTubePlayerView = dialogView.findViewById(R.id.youtube_player_view_all);
        youTubePlayerView.getPlayerUiController().showFullscreenButton(false);
        youTubePlayerView.getPlayerUiController().showBufferingProgress(true);
        youTubePlayerView.getPlayerUiController().showYouTubeButton(false);
        youTubePlayerView.getPlayerUiController().showVideoTitle(false);
        youTubePlayerView.getPlayerUiController().showMenuButton(false);
        getLifecycle().addObserver(youTubePlayerView);

        playVideo("", youtubeurl, "dd", "dd", true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }

                alertDialog.dismiss();
                youTubePlayerView.release();
              */
/*  Intent in = new Intent(getActivity(), PlayWithCash.class);
//                in.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                startActivity(in);*//*



            }
        });


    }

    private void headerPlayUrlNHL(String surl, final String scode) {


        final Dialog dialogView = new Dialog(context);
        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView.setContentView(R.layout.dialog_promo_redeem1);
        dialogView.setCancelable(false);

        Button btnViewAllPromos = dialogView.findViewById(R.id.buttonViewAllPromotions);

        Button btnRedeem = dialogView.findViewById(R.id.btnRedeem);
        ImageView cancel = dialogView.findViewById(R.id.imageViewcancel);
        ToggleButton fullscreen_button_lol = dialogView.findViewById(R.id.fullscreen_button_lol);


        dialogView.show();

        Window window = dialogView.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialogView.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnViewAllPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView.dismiss();
                if (dialogView.isShowing()) {
                    dialogView.dismiss();
                }



               */
/* Fragment PromoFragment = new PromosFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, PromoFragment); // give your fragment container id in first parameter
                fragmentTransaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                fragmentTransaction.commit();*//*

                dialogView.dismiss();


            }
        });
        fullscreen_button_lol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TvFullScreenVideo.class);
                intent.putExtra("video_id", scode);
                intent.putExtra("video_time", "3.00");
                context.startActivity(intent);
            }
        });
        btnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PlayWithCash.class);
                intent.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                if (dialogView.isShowing()) {
                    dialogView.dismiss();
                }

                timer = new Timer();
                dialogView.dismiss();
                intent.putExtra("referredby", "");
                context.startActivity(intent);
            }
        });
        youTubePlayerView = dialogView.findViewById(R.id.youtube_player_view_all);
        youTubePlayerView.getPlayerUiController().showFullscreenButton(false);
        youTubePlayerView.getPlayerUiController().showBufferingProgress(true);
        youTubePlayerView.getPlayerUiController().showYouTubeButton(false);
        youTubePlayerView.getPlayerUiController().showVideoTitle(false);
        youTubePlayerView.getPlayerUiController().showMenuButton(false);

//            context.addObserver(youTubePlayerView);
        playVideo("", surl, "dd", "dd", true);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogView.isShowing()) {
                    dialogView.dismiss();
                }

                dialogView.dismiss();
                youTubePlayerView.release();
//                youTubePlayerView.clearAnimation();
              */
/*  Intent in = new Intent(getActivity(), PlayWithCash.class);
//                in.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                startActivity(in);*//*



            }
        });


    }

        */
/*private void headerPlayUrlNHL(String youtubeurl) {
            ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);

            View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_promo_redeem1, viewGroup, false);

            Button btnViewAllPromos = dialogView.findViewById(R.id.buttonViewAllPromotions);

            Button btnRedeem = dialogView.findViewById(R.id.btnRedeem);
            ImageView cancel = dialogView.findViewById(R.id.imageViewcancel);

            // dialogView.setBackgroundColor(getResources().getColor(R.color.transparent));


            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();

            alertDialog.setCancelable(false);
            Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            //alertDialog.setBackgroundDrawable(Color.TRANSPARENT);
            alertDialog.show();


            btnViewAllPromos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
               *//*
*/
/* Fragment PromoFragment = new PromosFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();//getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, PromoFragment); // give your fragment container id in first parameter
                fragmentTransaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                fragmentTransaction.commit();*//*
*/
/*
                    alertDialog.dismiss();
                    Activity act = getActivity();
                    if (act instanceof Dashboard)
                        ((Dashboard) act).setPromo();
                }
            });

            btnRedeem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), PlayWithCash.class);
//                intent.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                    startActivity(intent);
                }
            });

            youTubePlayerView = dialogView.findViewById(R.id.youtube_player_view_all);
            youTubePlayerView.getPlayerUiController().showFullscreenButton(false);
            youTubePlayerView.getPlayerUiController().showBufferingProgress(true);
            youTubePlayerView.getPlayerUiController().showYouTubeButton(false);
            youTubePlayerView.getPlayerUiController().showVideoTitle(false);
            youTubePlayerView.getPlayerUiController().showMenuButton(false);
            getLifecycle().addObserver(youTubePlayerView);

            playVideo("", youtubeurl, "dd", "dd", true);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }

                    alertDialog.dismiss();
                    youTubePlayerView.release();
              *//*
*/
/*  Intent in = new Intent(getActivity(), PlayWithCash.class);
//                in.putExtra("SIGN_UP_BONUS", "SIGNUPBONUS5");
                startActivity(in);*//*
*/
/*


                }
            });


        }*//*




        */
/*@Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        recyclerViewItems_NewFliter = recyclerViewItems;

//                        MyCardsPojo all_home_cards = (MyCardsPojo) recyclerViewItems.get(position);            if (recyclerViewItems.get(position) instanceof MyCardsPojo) {
                    } else {
                        List<Object> filteredList = new ArrayList<>();
                        if (recyclerViewItems.size() > 0) {


                            for (int i = 0; i < recyclerViewItems.size(); i++) {

                                for (Object row12 : recyclerViewItems) {


                                    // name match condition. this might differ depending on your requirement
                                    // here we are looking for name or phone number match
                                    if (all_home_rows.getCardType().contains(charString.toLowerCase()) ) {
                                        filteredList.add(row12);
                                    }
                                }


                            }

                        }

                        recyclerViewItems_NewFliter = filteredList;

                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = recyclerViewItems_NewFliter;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    recyclerViewItems_NewFliter = (ArrayList<Object>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }*//*

}

*/
