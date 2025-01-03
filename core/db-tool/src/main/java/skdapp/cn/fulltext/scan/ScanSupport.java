package skdapp.cn.fulltext.scan;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import skdapp.cn.fulltext.constant.Constant;
import skdapp.cn.fulltext.util.Assert;

/**
 * SPRING扫描包指定路径的包
 * 
 * @project common-utils
 * @fileName Scaner.java
 * @Description
 * @author light-zhang
 * @date 2019年5月5日
 * @version 1.0.0
 */
public class ScanSupport implements ResourceLoaderAware {
	/**
	 * 注入ResourceLoader让Spring管理
	 */
	private ResourceLoader resourceLoader;

	private ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);

	/**
	 * set注入对象
	 */
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * 利用spring提供的扫描包下面的类信息,再通过classfrom反射获得类信息
	 * 
	 * @param scanPath
	 * @return
	 * @throws IOException
	 */
	public Set<Class<?>> doScan(String[] scanPath) throws IOException {
		if (StringUtils.isEmpty(scanPath)) {
			return null;
		}
		Set<Class<?>> classes = new HashSet<Class<?>>();
		MetadataReader metadataReader = null;
		String packageSearchPath = null;
		Resource[] resources = null;
		for (String path : scanPath) {
			packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					.concat(ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(path))
							.concat("/**/*.class"));
			resources = resolver.getResources(packageSearchPath);
			for (Resource resource : resources) {
				if (resource.isReadable()) {
					metadataReader = metadataReaderFactory.getMetadataReader(resource);
					if (metadataReader.getClassMetadata().isConcrete()) {// 当类型不是抽象类或接口在添加到集合
						try {
							classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
						} catch (ClassNotFoundException e) {
							Assert.IllegalArgumentException(true, e.getMessage());
						}
					}
				}
			}
		}
		return classes;
	}

	/**
	 * 指定包下面的类信息
	 * 
	 * @return 多个类信息
	 */
	public static Set<Class<?>> classInfos(String[] scanPath) {
		try {
			return new ScanSupport().doScan(scanPath);
		} catch (Exception e) {
			Assert.serviceException(Constant.DOSCAN_ERROR, e);
		}
		return null;
	}
}
