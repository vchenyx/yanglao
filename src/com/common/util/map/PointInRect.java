package com.common.util.map;



public class PointInRect {

	/**
	 * 判断点是否在一个多边形里
	 * 
	 * @param pCur
	 *            需要判断位置的点
	 * @param positionBeans
	 *            组成多边形的顶点集合
	 * @return
	 */
	public static boolean point_in_rect(PositionBean pCur,
			PositionBean[] positionBeans) {
		// 任意多边形有N个顶点
		int nCount = positionBeans.length;
		int nCross = 0;
		for (int i = 0; i < nCount; i++) {
			// 依次取相邻的两个点
			PositionBean pStart = positionBeans[i];
			PositionBean pEnd = positionBeans[(i + 1) % nCount];

			// 相邻的两个点是平行于x轴的，肯定不相交，忽略
			if (pStart.getY_pos() == pEnd.getY_pos())
				continue;

			// 交点在pStart,pEnd的延长线上，pCur肯定不会与pStart.pEnd相交，忽略
			if (pCur.getY_pos() < Math
					.min((pStart.getY_pos()), pEnd.getY_pos())
					|| pCur.getY_pos() > Math.max(pStart.getY_pos(),
							pEnd.getY_pos()))
				continue;

			// 求当前点和x轴的平行线与pStart,pEnd直线的交点的x坐标
			double x = (double) (pCur.getY_pos() - pStart.getY_pos())
					* (double) (pEnd.getX_pos() - pStart.getX_pos())
					/ (double) (pEnd.getY_pos() - pStart.getY_pos())
					+ pStart.getX_pos();

			// 若x坐标大于当前点的坐标，则有交点
			if (x > pCur.getX_pos())
				nCross++;
		}
		// GlobalClass.m_logger.info("("+pCur.getX_pos()+","+pCur.getY_pos()+")*"+nCross+"*"+(nCross
		// % 2 == 1));
		// 单边交点为偶数，点在多边形之外
		return (nCross % 2 == 1);
	}
}
