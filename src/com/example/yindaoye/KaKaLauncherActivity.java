package com.example.yindaoye;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * ��Activity
 * 
 * @author ansen
 * @create time 2015-08-07
 */
public class KaKaLauncherActivity extends FragmentActivity
{
    private GuideViewPager vPager;
    private List<LauncherBaseFragment> list = new ArrayList<LauncherBaseFragment>();
    private BaseFragmentAdapter adapter;

    private ImageView[] tips;
    private int currentSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_luancher_main);
	initView();
	initFragment();
    }

    /**
     * ��ʼ���ؼ�
     */
    private void initView()
    {
	// ��ʼ������ؼ�
	ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
	tips = new ImageView[3];
	for (int i = 0; i < tips.length; i++)
	{
	    ImageView imageView = new ImageView(this);
	    imageView.setLayoutParams(new LayoutParams(10, 10));
	    if (i == 0)
	    {
		imageView.setBackgroundResource(R.drawable.page_indicator_focused);
	    }
	    else
	    {
		imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
	    }
	    tips[i] = imageView;
	    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
		    new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    layoutParams.leftMargin = 20;// ���õ���view����߾�
	    layoutParams.rightMargin = 20;// ���õ���view���ұ߾�
	    group.addView(imageView, layoutParams);
	}

	// ��ȡ�Զ���viewpager Ȼ�����ñ���ͼƬ
	vPager = (GuideViewPager) findViewById(R.id.viewpager_launcher);
	vPager.setBackGroud(BitmapFactory.decodeResource(getResources(),
		R.drawable.bg_kaka_launcher));
    }

    /**
     * ��ʼ��fragment
     */
    private void initFragment()
    {
	/**
	 * ��ʼ������fragment ������ӵ�list��
	 */
	RewardLauncherFragment rewardFragment = new RewardLauncherFragment();
	PrivateMessageLauncherFragment privateFragment = new PrivateMessageLauncherFragment();
	StereoscopicLauncherFragment stereoscopicFragment = new StereoscopicLauncherFragment();
	list.add(rewardFragment);
	list.add(privateFragment);
	list.add(stereoscopicFragment);

	adapter = new BaseFragmentAdapter(getSupportFragmentManager(), list);
	vPager.setAdapter(adapter);
	vPager.setOffscreenPageLimit(2);
	vPager.setCurrentItem(0);
	vPager.setOnPageChangeListener(changeListener);
    }

    /**
     * ����viewpager���ƶ�
     */
    OnPageChangeListener changeListener = new OnPageChangeListener()
    {
	@Override
	public void onPageSelected(int index)
	{
	    setImageBackground(index);// �ı������л�Ч��
	    LauncherBaseFragment fragment = list.get(index);

	    list.get(currentSelect).stopAnimation();// ֹͣǰһ��ҳ��Ķ���
	    fragment.startAnimation();// ������ǰҳ��Ķ���

	    currentSelect = index;
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{}
    };

    /**
     * �ı������л�Ч��
     * 
     * @param selectItems
     */
    private void setImageBackground(int selectItems)
    {
	for (int i = 0; i < tips.length; i++)
	{
	    if (i == selectItems)
	    {
		tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
	    }
	    else
	    {
		tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
	    }
	}
    }
}
