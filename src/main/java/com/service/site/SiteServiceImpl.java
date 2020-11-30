package com.service.site;

import com.dao.AttAchDao;
import com.dao.CommentDao;
import com.dao.ContentDao;
import com.dao.MetaDao;
import com.idrot.CommentCond;
import com.idrot.ContentCond;
import com.idrot.StatisticsDto;
import com.exce.Types;
import com.entity.CommentDomain;
import com.entity.ContentDomain;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteServiceImpl.class);
    
    @Autowired
    private CommentDao commentDao;
    
    @Autowired
    private ContentDao contentDao;
    
    @Autowired
    private MetaDao metaDao;
    
    @Autowired
    private AttAchDao attAchDao;



    @Override
    @Cacheable(value = "siteCache", key = "'comments_' + #p0")
    public List<CommentDomain> getComments(int limit) {
        LOGGER.debug("Enter recentComments method: limit={}", limit);
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        PageHelper.startPage(1,limit);
        List<CommentDomain> rs = commentDao.getCommentsByCond(new CommentCond());
        LOGGER.debug("Exit recentComments method");
        return rs;
    }
    
    @Override
    @Cacheable(value = "siteCache", key = "'newArticles_' + #p0")
    public List<ContentDomain> getNewArticles(int limit) {
        LOGGER.debug("Enter recentArticles method:limit={}",limit);
        if (limit < 0 || limit > 10) {
            limit = 10;
        }
        PageHelper.startPage(1,limit);
        List<ContentDomain> rs = contentDao.getArticleByCond(new ContentCond());
        LOGGER.debug("Exit recentArticles method");
        return rs;
    }
    
    @Override
    @Cacheable(value = "siteCache", key = "'statistics_'")
    public StatisticsDto getStatistics() {
        LOGGER.debug("Enter recentStatistics method");
    
        // 文章总数
        Long articles = contentDao.getArticleCount();
    
        // 评论总数
        Long comments = commentDao.getCommentCount();
    
        // 链接数
        Long links = metaDao.getMetasCountByType(Types.LINK.getType());
    
        // 获取附件数
        Long attAches = attAchDao.getAttAchCount();
    
        StatisticsDto rs = new StatisticsDto();
        rs.setArticles(articles);
        rs.setComments(comments);
        rs.setLinks(links);
        rs.setAttachs(attAches);
        LOGGER.debug("Exit recentStatistics method");
        return rs;
    }
}
