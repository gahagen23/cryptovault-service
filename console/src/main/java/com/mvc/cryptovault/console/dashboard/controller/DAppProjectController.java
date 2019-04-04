package com.mvc.cryptovault.console.dashboard.controller;

import com.mvc.cryptovault.console.common.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiyichen
 * @create 2018/11/21 16:45
 */
@RestController
@RequestMapping("dashboard/appProject")
public class DAppProjectController extends BaseController {
//    @Autowired
//    AppProjectService appProjectService;
//    @Autowired
//    AppProjectUserTransactionService appProjectUserTransactionService;
//    @Autowired
//    CommonPairService commonPairService;
//@Autowired
//private CommonTokenService commonTokenService;
//    @DeleteMapping("{id}")
//    public Result<Boolean> deleteProject(@PathVariable("id") BigInteger id) {
//        var appProject = appProjectService.findById(id);
//        if (null == appProject) {
//            return new Result<>(true);
//        }
//        //只有未展示的项目才可以删除
//        Assert.isTrue(null != appProject && appProject.getVisiable() == 0, "项目已展示,无法删除");
//        //有订单的项目不能删
//        Boolean result = appProjectUserTransactionService.existTrans(id);
//        Assert.isTrue(!result, "已有交易无法删除");
//        appProjectService.deleteById(id);
//        appProjectService.updateAllCache("id desc");
//        appProjectService.updateCache(id);
//        return new Result<>(true);
//    }
//
//    @PutMapping("")
//    public Result<Boolean> updateProject(@RequestBody DProjectDTO dProjectDTO) {
//        AppProject appProject = new AppProject();
//        BeanUtils.copyProperties(dProjectDTO, appProject);
//        appProject.setPairId(BigInteger.ZERO );
//        appProject.setTokenName(commonTokenService.getTokenName(dProjectDTO.getTokenId()));
//        appProject.setBaseTokenName(commonTokenService.getTokenName(dProjectDTO.getBaseTokenId()));
//        appProject.setUpdatedAt(System.currentTimeMillis());
//        appProjectService.update(appProject);
//        appProject = appProjectService.findById(dProjectDTO.getId());
//        String key = "AppProject".toUpperCase() + "_" + dProjectDTO.getId();
//        redisTemplate.opsForValue().set(key, JSON.toJSONString(appProject), 24, TimeUnit.HOURS);
//        appProjectService.updateAllCache("id desc");
//        appProjectService.updateCache(dProjectDTO.getId());
//        return new Result<>(true);
//    }
//
//    @PostMapping("")
//    public Result<Boolean> newProject(@RequestBody DProjectDTO dProjectDTO) {
//        appProjectService.newProject(dProjectDTO);
//        appProjectService.updateAllCache("id desc");
//        return new Result<>(true);
//    }
//
//    @GetMapping("{id}")
//    public Result<DProjectDetailVO> getDetail(@PathVariable("id") BigInteger id) {
//        AppProject appProject = appProjectService.findById(id);
//        DProjectDetailVO vo = new DProjectDetailVO();
//        BeanUtils.copyProperties(appProject, vo);
//        return new Result<>(vo);
//    }
//
//    @GetMapping("")
//    public Result<PageInfo<DProjectVO>> projects(@ModelAttribute PageDTO pageDTO) {
//        PageInfo<DProjectVO> result = appProjectService.projects(pageDTO);
//        return new Result<>(result);
//    }
//
//
//    @GetMapping("{id}/partake")
//    Result<List<ExportPartake>> exportPartake(@PathVariable("id") BigInteger id) throws InterruptedException {
//        List<ExportPartake> result = appProjectService.exportPartake(id);
//        return new Result<>(result);
//    }
//
//    @PostMapping("{id}/partake")
//    Result<Boolean> importPartake(@RequestBody List<ImportPartake> list, @RequestParam("fileName") String fileName) {
//        String key = RedisConstant.PARTAKE_IMPORT + fileName;
//        if (null != redisTemplate.opsForValue().get(key)) {
//            throw new IllegalArgumentException("该文件正在导入,请稍后");
//        }
//        appProjectService.importPartake(list, fileName);
//        return new Result<>(true);
//    }

}
