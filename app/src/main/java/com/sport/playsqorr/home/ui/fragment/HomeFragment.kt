package com.sport.playsqorr.home.ui.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.facebook.login.LoginManager
import com.sport.playsqorr.R
import com.sport.playsqorr.SportIdMatchupTypeInterface
import com.sport.playsqorr.dynamic.*
import com.sport.playsqorr.home.`interface`.IntentInterface
import com.sport.playsqorr.home.ui.adapter.HomeCardsAdapter
import com.sport.playsqorr.home.ui.adapter.MenuAdapter
import com.sport.playsqorr.home.ui.adapter.SubMenuAdapter
import com.sport.playsqorr.home.viewmodel.HomeViewModelFactory
import com.sport.playsqorr.home.viewmodel.HomeViewmodel
import com.sport.playsqorr.matchup.ui.activity.CardTypeMatchupActivity
import com.sport.playsqorr.matchup.ui.activity.CardTypePlayTacToeActivity
import com.sport.playsqorr.pojos.MyCardsPojo
import com.sport.playsqorr.utilities.APIs
import com.sport.playsqorr.utilities.AppConstants
import com.sport.playsqorr.utilities.Utilities
import com.sport.playsqorr.views.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), SportIdMatchupTypeInterface,IntentInterface {
    var viewmodelHome: HomeViewmodel? = null

    var mContext: Context? = null
    var rvMenu: RecyclerView? = null
    var rvSubMenu: RecyclerView? = null
    var rvCards: RecyclerView? = null
    var menuAdapter: MenuAdapter? = null
    var subMenuAdapter: SubMenuAdapter? = null
    var adapterHomeCards: HomeCardsAdapter? = null
    var menuArrayList: ArrayList<MenuData>? = null
    var subMenuArrayList: ArrayList<Leagues>? = null
    var listMyCardPojo = ArrayList<MyCardsPojo>()
    var sportId = -1
    var utilities = Utilities()

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        //  viewmodelHome?.callGetCardsApi()
        mContext = requireContext()
        rvMenu = getView()!!.findViewById<View>(R.id.rvmenu) as RecyclerView
        rvSubMenu = getView()!!.findViewById<View>(R.id.rvSubmenu) as RecyclerView
        rvCards = getView()!!.findViewById<View>(R.id.rvCards) as RecyclerView
        rvMenu!!.layoutManager = LinearLayoutManager(mContext)
        menuArrayList = ArrayList()
        val obj = MenuData("0", "ALL SPORTS", "0", ArrayList())
        menuArrayList!!.add(0, obj)
        /* menuAdapter = activity?.let {
            MenuAdapter(
                menuArrayList!!,
                mContext!!,
                it,
                this@HomeFragment,
                this
            )
        }*/
        val menuLayout: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvMenu!!.layoutManager = menuLayout
        rvMenu!!.itemAnimator = DefaultItemAnimator()
        rvMenu!!.adapter = menuAdapter
        /** Create handle for the RetrofitInstance interface */
        val service = RetrofitInstance.getRetrofitInstance().create(
            RetrofitDataService::class.java
        )
        val menuCall = service.getmenu()
        Log.wtf("URL Called", menuCall.request().url().toString() + "")
        menuCall.enqueue(object : Callback<ArrayList<MenuData>> {

            override fun onResponse(
                call: Call<ArrayList<MenuData>>,
                response: Response<ArrayList<MenuData>>
            ) {
                println("response check -->" + response.body())
                menuArrayList!!.addAll(response.body())

                for (i in response.body()) {
                    println("jira response menuadpter ${i.strName}")
                }
                for (item in menuArrayList!!) {
                    println("jira menuadapter response ${item.strName}")
                }

                menuAdapter = activity?.let {
                    MenuAdapter(
                        menuArrayList!!,
                        mContext!!,
                        it,
                        this@HomeFragment
                    ) { sportId: Int, leagueId: Int -> setSportAndMatchupType(sportId, leagueId) }
                }
                val menuLayout: RecyclerView.LayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                rvMenu!!.layoutManager = menuLayout
                rvMenu!!.itemAnimator = DefaultItemAnimator()
                rvMenu!!.adapter = menuAdapter
            }

            override fun onFailure(call: Call<ArrayList<MenuData>>, t: Throwable) {}
        })

        if (!Utilities.isNetworkAvailable(activity)) {
            Utilities.showNoInternetAlert(activity)
        } else {
            getHomeCardsFromServer("M")
            //getCardApiCall();
        }
    }

    fun init() {

        viewmodelHome =
            ViewModelProvider(
                this,
                HomeViewModelFactory(requireContext())
            )[HomeViewmodel::class.java]
    }

    fun refreshSubMenu(leagues: ArrayList<Leagues?>?) {
//        if (leagues == null)
//        {     leagues.clear(); }
        rvSubMenu!!.layoutManager = LinearLayoutManager(mContext)
        subMenuAdapter = mContext?.let {
            activity?.let { it1 ->
                SubMenuAdapter(
                    leagues,
                    it,
                    it1
                ) { sportId: Int, leagueId: Int -> setSportAndMatchupType(sportId, leagueId) }
            }
        }
        val menuLayout: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvSubMenu!!.layoutManager = menuLayout
        rvSubMenu!!.itemAnimator = DefaultItemAnimator()
        rvSubMenu!!.adapter = subMenuAdapter
    }

    // MATCHUP
    //To get data of ALL tab data of home
    private fun getHomeCardsFromServer(msg: String) {


        /* Ctype = "";
        Log.e("ALL API :: ", APIs.GET_CARDS);
        recyclerViewItems = new ArrayList<>();*/
        resetCardsAdapterData()
        AndroidNetworking.get(APIs.GET_CARDS)
            .setPriority(Priority.HIGH) //                .addHeaders("sessionToken", Dashboard.SESSIONTOKEN)
            .addHeaders("Authorization", "bearer " + Dashboard.NEWTOKEN)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {


                    //  recyclerViewItems.clear();
                    //   cardsResponse.clear();
                    Log.e("ALL +HOME :: ", response.toString())

                    // do anything with response
                    for (i in 0 until response.length()) {
                        try {
                            val jb = response.getJSONObject(i)


                            //  if (jb.getString("cardType").equalsIgnoreCase("MATCH-UP"))
                            run {

                                //     ll_top.setVisibility(View.GONE);
                                //   Ctype = "MATCH-UP";
                                val mp = MyCardsPojo()
                                val ja = jb.getJSONArray("playerImages")
                                if (ja.length() > 0) {
                                    val a1 = ja[0].toString()
                                    val a2 = ja[1].toString()
                                    mp.playerImageLeft = a1
                                    mp.playerImageRight = a2
                                }
                                mp.cardId = jb.getString("cardId")
                                mp.cardTitle = jb.getString("cardTitle")
                                mp.matchupType = jb.getString("matchupType")
                                mp.cardType = jb.getString("cardType")
                                mp.startTime = jb.getString("startTime")
                                mp.leagueId = jb.getString("leagueId")
                                mp.sportId = jb.getInt("sport_id")
                                mp.sport_name = jb.getString("sport_name")

//                                if(jb.getString("leagueSubTitle")!=null){
                                mp.leagueSubTitle = jb.getString("leagueSubTitle")
                                //                            }
                                mp.leagueAbbrevation = jb.getString("leagueAbbreviation")


                                if (mp.sportId != null && !viewmodelHome?.listSportId?.contains(mp.sportId)!!) {

                                    viewmodelHome!!.listSportId.add(mp.sportId)
                                }

                                if (mp.startTime != null) {
                                    mp.cardTime = utilities.getDifferenceOfTwoDates(
                                        utilities.currentDateAndTime,
                                        utilities.convertDateAndTime(mp.startTime)
                                    )
                                }

                                listMyCardPojo.add(mp)

                                //  mp.setIsPurchased(jb.getString("isPurchased"));
                                //   mp.setIsLive(jb.getString("isLive"));
                                Log.e(
                                    "cards--",
                                    jb.getJSONArray("playerCardIds").toString() + "----"
                                )
                                //                                playerCardIds
                                //         mp.setPlayerCardIds(player_ids);

                                //                                playerCardIds
                                //         mp.setPlayerCardIds(player_ids);
                                val pjson_ids = jb.getJSONArray("playerCardIds")
                               /* val player_ids: MutableList<String> = ArrayList()

                                for (j in 0 until pjson_ids.length()) {
                                    player_ids.add(pjson_ids[j].toString())
                                    println("jira homefragment playercardid ${pjson_ids[j].toString()}")

                                }*/
                              //  mp.playerCardIds = player_ids


                            } /*else {
                                  //  ll_top.setVisibility(View.VISIBLE);
                                }*/
                        } catch (e: JSONException) {
                            e.printStackTrace()

                        }
                    }


                    /*  handleDiffData("MATCH-UP");
                        setPageData("MATCH-UP");

                        progressBar.setVisibility(View.GONE);
                        if (recycleAdapter != null)
                            recycleAdapter.notifyDataSetChanged();*/
                    refreshCardsList(-1, -1)
                }

                override fun onError(error: ANError) {
                    // handle error
                    //progressBar.setVisibility(View.GONE);
                    if (error.errorCode != 0) {


                        /*  if (error.getErrorCode() == 401) {

                                Intent in_login = new Intent(getActivity(), Login.class);
                                startActivity(in_login);
                                getActivity().finish();
//                                Utilities.showToast(getActivity(), "Please");
                            } else {
                                Utilities.showToast(getActivity(), "Something went wrong,Please try again later.");
                            }
*/
                        if (error.errorCode == 403) {
                            Utilities.showToast(activity, getString(R.string.servererror_msg))
                        }
                        try {
                            val ej = JSONObject(error.errorBody)
                            //                            Utilities.showToast(getActivity(), ej.getString("message"));
//                            if (text.toString().contains(LINK))
                            val au = ej.getString("message")
                            val ab = ej.getString("error")
                            if (au.contains("Unauthorized") || au.contains("ab")) {
                                showAlertBox(
                                    activity,
                                    "Error",
                                    "Session has expired,please try logining again"
                                )
                            } else {
                                Utilities.showToast(activity, ej.getString("message"))
                            }
                        } catch (e: Exception) {
                        }
                    }
                }
            })
    }

    private fun showAlertBox(context: Context?, title: String, message: String) {

        // Create custom dialog object
        val dialog = Dialog(context!!)
        // Include dialog.xml file
        dialog.setContentView(R.layout.alerts)
        val window = dialog.window
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        // window.setGravity(Gravity.CENTER);
//        window.setGravity(Gravity.BOTTOM);
        dialog.show()
        dialog.setCancelable(false)
        val alert_title = dialog.findViewById<View>(R.id.alert_title) as TextView
        val alert_msg = dialog.findViewById<View>(R.id.alert_msg) as TextView
        alert_title.text = title
        alert_msg.text = message
        val alert_ok = dialog.findViewById<View>(R.id.alert_ok) as TextView
        alert_ok.text = "OK"
        // if decline button is clicked, close the custom dialog
        alert_ok.setOnClickListener { //                dialog.dismiss();
            val sp = activity!!.getSharedPreferences("SESSION_TOKEN", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.clear()
            editor.apply()

            //mydb.clearTableMobileUser();
            // myDbHelper.resetLocalData();
            LoginManager.getInstance().logOut()
            val `in` = Intent(activity, OnBoarding::class.java)
            `in`.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(`in`)
            // getApplicationContext().deleteDatabase(DataBaseHelper.DATABASE_NAME);


            //    resetDatabase();

            /*  Intent mStartActivity = new Intent(Profile.this, OnBoarding.class);
                    int mPendingIntentId = 123456;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(Profile.this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);*/
        }
    }

    fun resetCardsAdapterData() {
        listMyCardPojo.clear()
        viewmodelHome!!.listLeaguetId.clear()

    }

    fun refreshCardsList(sportId: Int, leagueId: Int) {
        rvCards!!.layoutManager = LinearLayoutManager(mContext)
        adapterHomeCards = HomeCardsAdapter(mContext, listMyCardPojo, sportId, leagueId,this)
        val menuLayout: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvCards!!.layoutManager = menuLayout
        rvCards!!.itemAnimator = DefaultItemAnimator()
        rvCards!!.adapter = adapterHomeCards
    }


    override fun setSportAndMatchupType(sportId: Int, leagueId: Int) {
        if (sportId != -2) {
            this.sportId = sportId
        }
        adapterHomeCards!!.updateList(this.sportId, leagueId)
    } /*
    public void getCardApiCall() {

        final API_class service = Retrofit_funtion_class.getClient().create(API_class.class);

        String Accept = "application/json";
        Call<MyCardsPojo> callRetrofit = null;

        callRetrofit = service.getCards(Accept, "bearer " +  Dashboard.NEWTOKEN);

        callRetrofit.enqueue(new Callback<MyCardsPojo>() {
            @Override
            public void onResponse(Call<MyCardsPojo> call, Response<MyCardsPojo> response) {

                if(response.isSuccessful())
                {

                    if(response.body() != null)
                    {


                    }
                }
               */


    /* System.out.println("----------------------------------------------------");
                Log.d("Call request", call.request().toString());
                Log.d("Call request header", call.request().headers().toString());
                Log.d("Response raw header", response.headers().toString());
                Log.d("Response raw", String.valueOf(response.raw().body()));
                Log.d("Response code", String.valueOf(response.code()));

                System.out.println("----------------------------------------------------");*/
    /*



            }

            @Override
            public void onFailure(Call<MyCardsPojo> call, Throwable t) {



            }


        });

    }
*/
    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    fun getTime() {
        var simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        var currentDateTime: Date
        var cardDateAndTime: Date

        try {
            //currentDateTime = simpleDateFormat.parse("23/10/2013 21:30:10");
            currentDateTime = utilities.getCurrentDateAndTime();
            cardDateAndTime = simpleDateFormat.parse("13/10/2013 20:35:55");

            utilities.getDifferenceOfTwoDates(currentDateTime, cardDateAndTime);

        } catch (e: ParseException) {
            e.printStackTrace();
        }
    }

    fun getMyCardPojoObject(cardId : String):MyCardsPojo?
    {
        for(index in listMyCardPojo.indices)
        {
            if(listMyCardPojo[index].cardId.equals(cardId,true))
            {
                return listMyCardPojo.get(index)
            }
        }
        return null
    }
     fun callMoreCardsMatchupScreen(cardId: String) {

        println("jira callMoreCardsMatchupScreen")

        var myCardPojoObject = getMyCardPojoObject(cardId)

        if(myCardPojoObject?.cardType.equals(AppConstants.playTacToe,true))
        {
            context!!.startActivity(Intent(context, CardTypePlayTacToeActivity::class.java))

        }
        else if(myCardPojoObject?.cardType.equals(AppConstants.matchUp,true))
        {
            context!!.startActivity(Intent(context, CardTypeMatchupActivity::class.java))

        }







        /*val matchup = Intent(context, MoreCards_MatchupScreen::class.java)
        matchup.putExtra("home", "1")
        matchup.putExtra("cardid", cardId)
        matchup.putExtra("cardid_title", cardTitle)
        matchup.putExtra("cardid_color1", leagueId)
        matchup.putExtra("place", "homecards")
        matchup.putExtra("playerid_m", "0")
        activity?.let {
            val intent = Intent(it, HomeCards_MoreScreen::class.java)
            it.startActivity(intent)
        }*/


       /* val matchup_play = Intent(context, MatchupScreen::class.java)
        matchup_play.putExtra("morecards_info", myCardPojoObject)
        matchup_play.putExtra("cardid", cardId)
        matchup_play.putExtra("home", "1")
        matchup_play.putExtra("cardid_title", myCardPojoObject?.cardTitle)
        matchup_play.putExtra("cardid_color1",myCardPojoObject?.leagueAbbrevation)
        matchup_play.putExtra("place", "home")
        matchup_play.putExtra("cardid_date", "")
        matchup_play.putExtra("playerid_m", "")
        activity?.let {
            val intent = Intent(it, MatchupScreen::class.java)
            it.startActivity(matchup_play)
        }
*/



           /* Log.e("size---", "--1918---l")
            val matchup = Intent(context, MatchupScreen::class.java)
            matchup.putExtra("home", "1")
            matchup.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup.putExtra("place", "homecards")
            matchup.putExtra("position_data", true)
            matchup.putExtra("card_type", myCardPojoObject?.getCardType())
            matchup.putExtra("playerid_m", "")
            matchup.putExtra("cardid_date", myCardPojoObject?.cardTime)
            context!!.startActivity(matchup)*/

    }


    override fun callNextScreen(cardId: String) {
        var myCardPojoObject = getMyCardPojoObject(cardId)

        if (myCardPojoObject?.getCardType().equals("PLAY TAC TOE",true)) {
            val matchup_play = Intent(context, PlayPickGo_MatchupScreen::class.java)
            matchup_play.putExtra("home", "1")
            matchup_play.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup_play.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup_play.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup_play.putExtra("place", "homecards")
            matchup_play.putExtra("position_data", false)
            matchup_play.putExtra("playerid_m", "")
            if (myCardPojoObject?.startTime != null && !myCardPojoObject?.startTime.equals("")) {
                val formatTimeDiff: String = Utilities.getTimeDiff(myCardPojoObject?.startTime)
                if (formatTimeDiff != null && formatTimeDiff != "") {
                    matchup_play.putExtra("cardid_date", formatTimeDiff)
                }
            } else {
                matchup_play.putExtra("cardid_date", "")
            }
            context!!.startActivity(matchup_play)
        } else if (myCardPojoObject?.getCardType().equals("WIN PLAY SHOW",true)) {

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
        } else if (myCardPojoObject?.getCardType().equals("WIN PLAY SQOR",true)) {
            val matchup_play = Intent(context, Matchup_WinPlayGoTimeTwo::class.java)
            matchup_play.putExtra("home", "1")
            matchup_play.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup_play.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup_play.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup_play.putExtra("place", "homecards")
            matchup_play.putExtra("position_data", false)
            matchup_play.putExtra("playerid_m", "")
            matchup_play.putExtra("card_type", myCardPojoObject?.getCardType())
            if (myCardPojoObject?.startTime != null && !myCardPojoObject?.startTime.equals("")) {
                val formatTimeDiff: String = Utilities.getTimeDiff(myCardPojoObject?.startTime)
                if (formatTimeDiff != null && formatTimeDiff != "") {
                    matchup_play.putExtra("cardid_date", formatTimeDiff)
                }
            } else {
                matchup_play.putExtra("cardid_date", "")
            }
            context!!.startActivity(matchup_play)
        } else if (myCardPojoObject?.getCardType().equals("PLAY A PICK",true)) {
//
//
            Log.e("size---", "--1918---l")
            val matchup = Intent(context, MatchupScreen_PlayAPick::class.java)
            matchup.putExtra("home", "1")
            matchup.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup.putExtra("place", "homecards")
            matchup.putExtra("position_data", false)
            matchup.putExtra("card_type", myCardPojoObject?.getCardType())
            matchup.putExtra("playerid_m", "")
            if (myCardPojoObject?.startTime != null && !myCardPojoObject?.startTime.equals("")) {
                val formatTimeDiff: String = Utilities.getTimeDiff(myCardPojoObject?.startTime)
                if (formatTimeDiff != null && formatTimeDiff != "") {
                    matchup.putExtra("cardid_date", formatTimeDiff)
                }
            } else {
                matchup.putExtra("cardid_date", "")
            }
        } else if (myCardPojoObject?.getCardType().equals("WIN PLAY GO",true)) {
//
//
            Log.e("size---", "--1918---l" + myCardPojoObject?.getCardType())
            val matchup = Intent(context, Matchup_WinPlayGoTimeTwo::class.java)
            matchup.putExtra("home", "1")
            matchup.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup.putExtra("place", "homecards")
            matchup.putExtra("position_data", false)
            matchup.putExtra("card_type", myCardPojoObject?.getCardType())
            matchup.putExtra("playerid_m", "")
          //  matchup.putExtra("cardid_date", myCardPojoObject?.cardTime)

            if (myCardPojoObject?.startTime != null && !myCardPojoObject?.startTime.equals("")) {
                val formatTimeDiff: String = Utilities.getTimeDiff(myCardPojoObject?.startTime)
                if (formatTimeDiff != null && formatTimeDiff != "") {
                    matchup.putExtra("cardid_date", formatTimeDiff)
                }
            } else {
                matchup.putExtra("cardid_date", "")
            }
            context!!.startActivity(matchup)
        } else {
            System.out.println("jira home fragment MatchupScreen")
            Log.e("size---", "--1941---l")
            val matchup = Intent(context, MatchupScreen::class.java)
            matchup.putExtra("home", "1")
            matchup.putExtra("cardid", myCardPojoObject?.getCardId())
            matchup.putExtra("cardid_title", myCardPojoObject?.getCardTitle())
            matchup.putExtra("cardid_color1", myCardPojoObject?.getLeagueAbbrevation())
            matchup.putExtra("place", "homecards")
            matchup.putExtra("position_data", false)
            matchup.putExtra("card_type", myCardPojoObject?.getCardType())
            matchup.putExtra("playerid_m", "")
          //  matchup.putExtra("cardid_date", myCardPojoObject?.cardTime)

             if (myCardPojoObject?.startTime != null && !myCardPojoObject?.startTime.equals("")) {
                 val formatTimeDiff: String = Utilities.getTimeDiff(myCardPojoObject?.startTime)
                 if (formatTimeDiff != null && formatTimeDiff != "") {
                     matchup.putExtra("cardid_date", formatTimeDiff)
                 }
             } else {
                 matchup.putExtra("cardid_date", "")
             }
            context!!.startActivity(matchup)
        }
    }

}